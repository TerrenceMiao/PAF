var converter = new Showdown.converter();

var Comment = React.createClass({displayName: "Comment",

    render: function () {
        var rawMarkup = converter.makeHtml(this.props.children.toString());
        return (
            React.createElement("div", {className: "comment"},
                React.createElement("h2", {className: "commentAuthor"}, this.props.author),
                React.createElement("span", {dangerouslySetInnerHTML: {__html: rawMarkup}})
            )
        );
    }
});

var CommentList = React.createClass({displayName: "CommentList",

    render: function () {
        var commentNodes = this.props.data.map(function (comment) {
            return (
                React.createElement(Comment,
                    {author: comment.author}, comment.text,
                    comment.streetNumber + " " + comment.streetName, " " + comment.suburb + " " + comment.state + " " + comment.postcode
                )
            );
        });
        return (
            React.createElement("div", {className: "commentList"}, commentNodes)
        );
    }
});

var CommentForm = React.createClass({displayName: "CommentForm",

    handleSubmit: function (e) {

        e.preventDefault();

        var author = this.refs.author.getDOMNode().value.trim();
        var text = this.refs.text.getDOMNode().value.trim();

        var streetNumber = this.refs.streetNumber.getDOMNode().value.trim();
        var streetName = this.refs.streetName.getDOMNode().value.trim();
        var suburb = this.refs.suburb.getDOMNode().value.trim();
        var state = this.refs.state.getDOMNode().value.trim();
        var postcode = this.refs.postcode.getDOMNode().value.trim();

        this.props.onCommentSubmit({author: author, text: text,
            streetNumber: streetNumber, streetName: streetName,
            suburb: suburb, state: state, postcode: postcode
        });

        this.refs.author.getDOMNode().value = '';
        this.refs.text.getDOMNode().value = '';
        this.refs.streetNumber.getDOMNode().value = '';
        this.refs.streetName.getDOMNode().value = '';
        this.refs.suburb.getDOMNode().value = '';
        this.refs.state.getDOMNode().value = '';
        this.refs.postcode.getDOMNode().value = '';
    },
    render: function () {
        return (
            React.createElement("form", {className: "form-address", onSubmit: this.handleSubmit},
                React.createElement("input", {type: "text", className: "form-control", placeholder: "Street number", ref: "streetNumber"}),
                React.createElement("input", {type: "text", className: "form-control", placeholder: "Street name", ref: "streetName"}),
                React.createElement("input", {type: "text", className: "form-control", placeholder: "Suburb", ref: "suburb"}),
                React.createElement("input", {type: "text", className: "form-control", placeholder: "State", ref: "state"}),
                React.createElement("input", {type: "text", className: "form-control", placeholder: "Postcode", ref: "postcode"}),
                React.createElement("input", {type: "submit", className: "btn btn-lg btn-primary btn-block", value: "Got it"}),
                React.createElement("input", {type: "text", placeholder: "Your name", ref: "author"}),
                React.createElement("input", {type: "text", placeholder: "Say something...", ref: "text"})
            )
        );
    }
});

var CommentBox = React.createClass({displayName: "CommentBox",

    loadCommentsFromServer: function () {
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
    handleCommentSubmit: function (comment) {
        var comments = this.state.data;
        var newComments = comments.concat([comment]);
        this.setState({data: newComments});
        $.ajax({
            url: this.props.url,
            dataType: 'json',
            type: 'POST',
            data: JSON.stringify(comment),
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
        setInterval(this.loadCommentsFromServer, this.props.pollInterval);
    },
    render: function () {
        return (
            React.createElement("div", {className: "commentBox"},
                React.createElement("h1", null, "Postal Address"),
                React.createElement(CommentForm, {onCommentSubmit: this.handleCommentSubmit}),
                React.createElement("h1", null, "QR Code"),
                React.createElement(CommentList, {data: this.state.data})
            )
        );
    }
});


function renderOnClient(comments) {

    var data = comments || [];

    React.render(
        React.createElement(CommentBox, {data: data, url: "comments.json", pollInterval: 2000}),
        document.getElementById('content')
    );
}

function renderOnServer(comments) {

    var data = Java.from(comments);

    return React.renderToString(
        React.createElement(CommentBox, {data: data, url: "comments.json", pollInterval: 2000})
    );
}