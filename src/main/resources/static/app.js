var QRCodeComponent = React.createClass({displayName: "QRCodeComponent",

    propTypes: {
        text: React.PropTypes.string.isRequired,
        width: React.PropTypes.number,
        height: React.PropTypes.number,
        colorLight: React.PropTypes.string,
        colorDark: React.PropTypes.string
    },

    getDefaultProps: function() {
        return {
            width: 128,
            height: 128,
            colorLight: '#FFFFFF',
            colorDark: '#000000'
        };
    },

    componentDidMount: function() {
        this.update();
    },

    componentDidUpdate: function() {
        this.update();
    },

    update: function() {
        $(this.getDOMNode()).html('');

        var qrcode = new QRCode(this.getDOMNode(), {
            text: this.props.text,
            width: this.props.width,
            height: this.props.height,
            colorDark: this.props.colorDark,
            colorLight: this.props.colorLight
        });

        qrcode.makeCode(this.props.text);
    },

    render: function () {
        return (
            React.createElement("span")
        );
    }
});

var PostalAddress = React.createClass({displayName: "PostalAddress",

    render: function () {
        return (
            React.createElement("div", {className: "postalAddress"},
                React.createElement("div", {className: "qrcode"},
                    React.createElement(QRCodeComponent, {text: this.props.dpid + " || " + this.props.addressee + " || " + this.props.children.toString()})
                ),
                React.createElement("h2", {className: "addressee"}, this.props.addressee),
                React.createElement("h4", {dangerouslySetInnerHTML: {__html: this.props.children.toString()}})
            )
        );
    }
});

var PostalAddressList = React.createClass({displayName: "PostalAddressList",

    render: function () {
        var postalAddressNodes = this.props.data.map(function (postalAddress) {
            return (
                React.createElement(PostalAddress,
                    {dpid: postalAddress.dpid, addressee: postalAddress.addressee},
                    postalAddress.houseNumber1 + " " + postalAddress.streetName + " " + postalAddress.streetType,
                    " " + postalAddress.localityName + " " + postalAddress.state + " " + postalAddress.postcode
                )
            );
        });
        return (
            React.createElement("div", {className: "postalAddressList"}, postalAddressNodes)
        );
    }
});

var length = 0;

var PostalAddressForm = React.createClass({displayName: "PostalAddressForm",

    handleSubmit: function (e) {
        e.preventDefault();

        var addressee = this.refs.addressee.getDOMNode().value.trim();

        var houseNumber1 = this.refs.houseNumber1.getDOMNode().value.trim();
        var streetName = this.refs.streetName.getDOMNode().value.trim();
        var streetType = this.refs.streetType.getDOMNode().value.trim();
        var localityName = this.refs.localityName.getDOMNode().value.trim();
        var state = this.refs.state.getDOMNode().value.trim();
        var postcode = this.refs.postcode.getDOMNode().value.trim();

        if (!addressee || !houseNumber1 || !streetName || !streetType || !localityName || !state || !postcode) {
            return;
        }

        this.props.onPostalAddressSubmit({
            addressee: addressee,
            houseNumber1: houseNumber1, streetName: streetName, streetType: streetType,
            localityName: localityName, state: state, postcode: postcode
        });
    },

    componentDidUpdate: function () {
        if (length === 0) {
            length = this.props.data.length;
        } else if (length === this.props.data.length) {
            this.refs.addressee.getDOMNode().value = '';
            this.refs.houseNumber1.getDOMNode().value = '';
            this.refs.streetName.getDOMNode().value = '';
            this.refs.streetType.getDOMNode().value = '';
            this.refs.localityName.getDOMNode().value = '';
            this.refs.state.getDOMNode().value = '';
            this.refs.postcode.getDOMNode().value = '';

            length = 0;
        } else {
            length = 0;
        }
    },

    render: function () {
        return (
            React.createElement("form", {className: "form-address", onSubmit: this.handleSubmit},
                React.createElement("input", {type: "text", className: "form-control", placeholder: "To whom", ref: "addressee"}),
                React.createElement("input", {type: "text", className: "form-control", placeholder: "Street number", ref: "houseNumber1"}),
                React.createElement("input", {type: "text", className: "form-control", placeholder: "Street name", ref: "streetName"}),
                React.createElement("select", {className: "form-control", ref: "streetType"}, renderStreetTypeOptions(this.props.streetTypeData)),
                React.createElement("input", {type: "text", className: "form-control", placeholder: "Suburb", ref: "localityName"}),
                React.createElement("input", {type: "text", className: "form-control", placeholder: "State", ref: "state"}),
                React.createElement("input", {type: "text", className: "form-control", placeholder: "Postcode", ref: "postcode"}),
                React.createElement("input", {type: "submit", className: "btn btn-lg btn-primary btn-block", value: "Generate QR Code"})
            )
        );
    }
});

var PostalAddressBox = React.createClass({displayName: "PostalAddressBox",

    loadPostalAddressListFromServer: function () {
        $.ajax({
            url: this.props.url,
            dataType: 'json',
            success: function (data) {
                this.setState({data: data});
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.props.url, status, err.toString());
            }.bind(this)
        });
    },

    handlePostalAddressSubmit: function (postalAddress) {
        var postalAddresses = this.state.data;
        var newPostalAddresses = postalAddresses.concat([postalAddress]);
        this.setState({data: newPostalAddresses});
        $.ajax({
            url: this.props.url,
            dataType: 'json',
            type: 'POST',
            data: JSON.stringify(postalAddress),
            contentType: 'application/json',
            success: function (data) {
                this.setState({data: data});
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.props.url, status, err.toString());
            }.bind(this)
        });
    },

    getInitialState: function () {
        return {data: this.props.data || []};
    },

    componentDidMount: function () {
        setInterval(this.loadPostalAddressListFromServer, this.props.pollInterval);
    },

    render: function () {
        return (
            React.createElement("div", {className: "postalAddressBox"},
                React.createElement("h1", null, "Postal Address"),
                React.createElement(PostalAddressForm, {data: this.state.data, streetTypeData: this.props.streetTypeData,
                    onPostalAddressSubmit: this.handlePostalAddressSubmit}),
                React.createElement("h1", null, ""),
                React.createElement(PostalAddressList, {data: this.state.data})
            )
        );
    }
});

function renderStreetTypeOptions(streetTypeData) {
    var streetTypeOptions = [];

    for (var i = 0; i < streetTypeData.length; i++) {
        streetTypeOptions.push(
            React.createElement("option", {value: streetTypeData[i]}, streetTypeData[i])
        );
    }

    return streetTypeOptions;
}

function renderOnClient(postalAddressList, streetTypeList) {
    var data = postalAddressList || [];
    var streetTypeData = streetTypeList || [];

    React.render(
        React.createElement(
            PostalAddressBox, {data: data, streetTypeData: streetTypeData, url: "postaladdress.json", pollInterval: 200000}), document.getElementById('content')
    );
}

function renderOnServer(postalAddressList, streetTypeList) {
    var data = Java.from(postalAddressList);
    var streetTypeData = Java.from(streetTypeList);

    return React.renderToString(
        React.createElement(PostalAddressBox, {data: data, streetTypeData: streetTypeData, url: "postaladdress.json", pollInterval: 200000})
    );
}

