<%

    String message = (String) session.getAttribute("message");

    if (message == "Registration Successful!!") {

%>
<div class="alert alert-success alert-dismissible fade show" role="alert">
    <strong><%= message%></strong> 
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%            session.removeAttribute("message");

    }
    if (message == "Email must be unique!!") {
%>
<div class="alert alert-warning alert-dismissible fade show" role="alert">
    <strong><%= message%></strong> 
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%
        session.removeAttribute("message");
    }

    if (message == "Invalid email address or password!!") {
%>
<div class="alert alert-warning alert-dismissible fade show" role="alert">
    <strong><%= message%></strong> 
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%
        session.removeAttribute("message");
    }

%>
