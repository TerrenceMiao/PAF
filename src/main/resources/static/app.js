var converter = new Showdown.converter();

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
        var rawMarkup = converter.makeHtml(this.props.children.toString());
        return (
            React.createElement("div", {className: "postalAddress"},
                React.createElement("div", {id: "qrcode", className: "qrcode"}),
                React.createElement("h2", {className: "addressee"}, this.props.addressee),
                React.createElement("h4", {dangerouslySetInnerHTML: {__html: rawMarkup}})
            )
        );
    }
});

var PostalAddressList = React.createClass({displayName: "PostalAddressList",

    render: function () {
        var postalAddressNodes = this.props.data.map(function (postalAddress) {
            return (
                React.createElement(PostalAddress,
                    {addressee: postalAddress.addressee},
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
            houseNumber1: houseNumber1, streetName: streetName, streetType: streetType, localityName: localityName, state: state, postcode: postcode
        });

        this.refs.addressee.getDOMNode().value = '';
        this.refs.houseNumber1.getDOMNode().value = '';
        this.refs.streetName.getDOMNode().value = '';
        this.refs.streetType.getDOMNode().value = '';
        this.refs.localityName.getDOMNode().value = '';
        this.refs.state.getDOMNode().value = '';
        this.refs.postcode.getDOMNode().value = '';
    },
    render: function () {
        return (
            React.createElement("form", {className: "form-address", onSubmit: this.handleSubmit},
                React.createElement("input", {type: "text", className: "form-control", placeholder: "To whom", ref: "addressee"}),
                React.createElement("input", {type: "text", className: "form-control", placeholder: "Street number", ref: "houseNumber1"}),
                React.createElement("input", {type: "text", className: "form-control", placeholder: "Street name", ref: "streetName"}),
                React.createElement("input", {type: "text", className: "form-control", placeholder: "Street type", ref: "streetType"}),
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
                React.createElement(PostalAddressForm, {onPostalAddressSubmit: this.handlePostalAddressSubmit}),
                React.createElement("h1", null, ""),
                React.createElement(PostalAddressList, {data: this.state.data})
            )
        );
    }
});


function renderOnClient(postalAddresses) {

    var data = postalAddresses || [];

    React.render(
        React.createElement(PostalAddressBox, {data: data, url: "postaladdress.json", pollInterval: 2000}), document.getElementById('content')
    );

    React.render(
        React.createElement(QRCodeComponent, {text: "14382929 || Terrence Miao || 111 Bourke St, Melbourne VIC 3000"}), document.getElementById('qrcode')
    );
}

function renderOnServer(postalAddresses) {

    var data = Java.from(postalAddresses);

    return React.renderToString(
        React.createElement(PostalAddressBox, {data: data, url: "postaladdress.json", pollInterval: 2000})
    );
}
