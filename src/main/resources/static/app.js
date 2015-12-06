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

var PostalAddressForm = React.createClass({displayName: "PostalAddressForm",

    formSubmit: false,

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

        this.formSubmit = true;
    },

    clientPostalAddressListSize: 0,
    serverPostalAddressListSize: 0,

    isDpidFound: function(postalAddressListSize) {
        if (this.formSubmit) {
            if (this.clientPostalAddressListSize === 0) {
                // Client side update, at first in this place
                this.clientPostalAddressListSize = postalAddressListSize;
                return false;
            } else {
                // Server side update
                this.formSubmit = false;
                this.serverPostalAddressListSize = postalAddressListSize;

                if (this.clientPostalAddressListSize === this.serverPostalAddressListSize) {
                    // As SAME size Postal Address list as Client size update means DPID found
                    this.clientPostalAddressListSize = 0;
                    this.serverPostalAddressListSize = 0;

                    return true;
                } else {
                    // DIFFERENT size Postal Address list as Client size update means DPID NOT found
                    this.clientPostalAddressListSize = 0;
                    this.serverPostalAddressListSize = 0;

                    return false;
                }
            }
        } else {
            return false;
        }
    },

    componentDidMount: function() {
        this.refs.streetType.getDOMNode().value = '';
        this.refs.localityName.getDOMNode().value = '';
    },

    /*
     * Called every 2000ms due to form submit every 2000ms
     */
    componentDidUpdate: function () {
        if (this.isDpidFound(this.props.postalAddressListSize)) {
            // Get valid address
            var houseNumber1 = this.refs.houseNumber1.getDOMNode().value.trim();
            var streetName = this.refs.streetName.getDOMNode().value.trim();
            var streetType = this.refs.streetType.getDOMNode().value.trim();
            var localityName = this.refs.localityName.getDOMNode().value.trim();
            var state = this.refs.state.getDOMNode().value.trim();
            var postcode = this.refs.postcode.getDOMNode().value.trim();

            // Reset form
            this.refs.addressee.getDOMNode().value = '';
            this.refs.houseNumber1.getDOMNode().value = '';
            this.refs.streetName.getDOMNode().value = '';
            this.refs.streetType.getDOMNode().value = '';
            this.refs.localityName.getDOMNode().value = '';
            this.refs.state.getDOMNode().value = '';
            this.refs.postcode.getDOMNode().value = '';

            // Draw place on map
            document.getElementById('autocomplete').value = houseNumber1 + " " + streetName + " " + streetType + ", "
                + localityName + ", " + state + ", Australia";

            doQuery();
        }
    },

    suburbChange: function(event){
        for (var i = 0; i < this.props.suburbData.length; i++) {
            if (this.props.suburbData[i].localityName === event.target.value) {
                this.refs.state.getDOMNode().value = this.props.suburbData[i].state;
                this.refs.postcode.getDOMNode().value = this.props.suburbData[i].postcode;
            }
        }
    },

    render: function () {
        return (
            React.createElement("form", {className: "form-address", onSubmit: this.handleSubmit},
                React.createElement("input", {type: "text", className: "form-control", placeholder: "To whom", ref: "addressee"}),
                React.createElement("input", {type: "text", className: "form-control", placeholder: "Street number", ref: "houseNumber1"}),
                React.createElement("input", {type: "text", className: "form-control", placeholder: "Street name", ref: "streetName"}),
                React.createElement("select", {className: "form-control", ref: "streetType"},
                    renderStreetTypeOptions(this.props.streetTypeData)
                ),
                React.createElement("select", {className: "form-control", ref: "localityName", onChange: this.suburbChange},
                    renderSuburbOptions(this.props.suburbData)
                ),
                React.createElement("input", {type: "hidden", className: "form-control", placeholder: "State", ref: "state"}),
                React.createElement("input", {type: "hidden", className: "form-control", placeholder: "Postcode", ref: "postcode"}),
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
                React.createElement(PostalAddressForm, {
                    postalAddressListSize: this.state.data.length,
                    streetTypeData: this.props.streetTypeData,
                    suburbData: this.props.suburbData,
                    onPostalAddressSubmit: this.handlePostalAddressSubmit
                }),
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

function renderSuburbOptions(suburbData) {
    var suburbOptions = [];

    for (var i = 0; i < suburbData.length; i++) {
        suburbOptions.push(
            React.createElement("option", {value: suburbData[i].localityName},
                suburbData[i].localityName + " " + suburbData[i].state + " " + suburbData[i].postcode)
        );
    }

    return suburbOptions;
}

function renderOnClient(postalAddressList, streetTypeList, suburbList) {
    var data = postalAddressList || [];
    var streetTypeData = streetTypeList || [];
    var suburbData = suburbList || [];

    React.render(
        React.createElement(
            PostalAddressBox, {data: data, streetTypeData: streetTypeData, suburbData: suburbData,
                url: "postaladdress.json", pollInterval: 2000}), document.getElementById('content')
    );
}

function renderOnServer(postalAddressList, streetTypeList, suburbList) {
    var data = Java.from(postalAddressList);
    var streetTypeData = Java.from(streetTypeList);
    var suburbData = Java.from(suburbList);

    return React.renderToString(
        React.createElement(PostalAddressBox, {data: data, streetTypeData: streetTypeData, suburbData: suburbData,
            url: "postaladdress.json", pollInterval: 2000})
    );
}


