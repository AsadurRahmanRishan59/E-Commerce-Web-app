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

    if (message == "You are not logged in !! Login first") {
%>
<div class="alert alert-danger alert-dismissible fade show" role="alert">
    <strong><%= message%></strong> 
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%
        session.removeAttribute("message");
    }

    if (message == "You are not admin !! Can't access") {
%>
<div class="alert alert-danger alert-dismissible fade show" role="alert">
    <strong><%= message%></strong> 
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%
        session.removeAttribute("message");
    }
    if (message == "You are not user !! Can't access") {
%>
<div class="alert alert-danger alert-dismissible fade show" role="alert">
    <strong><%= message%></strong> 
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%
        session.removeAttribute("message");
    }





    String catTitle = (String) session.getAttribute("catTitle");
    if (message == "New Category Added") {
%>
<div class="alert alert-success alert-dismissible fade show" role="alert">
    <strong><%= "New Category " + catTitle + " Added"%></strong> 
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%
        session.removeAttribute("message");
        session.removeAttribute("catTitle");
    }




    if (message == "Could not be added-category") {
%>
<div class="alert alert-warning alert-dismissible fade show" role="alert">
    <strong><%= catTitle + " Could not be added"%></strong> 
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%
        session.removeAttribute("message");
        session.removeAttribute("catTitle");
    }



    if (message == "Category Title Must be Unique") {
%>
<div class="alert alert-warning alert-dismissible fade show" role="alert">
    <strong><%= catTitle + " already exists !! " + "\nCategory Title Must be Unique"%></strong> 
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%
        session.removeAttribute("message");
        session.removeAttribute("catTitle");
    }









    String productName = (String) session.getAttribute("productName");
    if (message == "New Product Added") {
%>
<div class="alert alert-success alert-dismissible fade show" role="alert">
    <strong><%= "New Product " + productName + " Added"%></strong> 
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%
        session.removeAttribute("message");
        session.removeAttribute("productName");
    }







    if (message == "Could not be added") {
%>
<div class="alert alert-warning alert-dismissible fade show" role="alert">
    <strong><%= productName + " Could not be added"%></strong> 
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%
        session.removeAttribute("message");
        session.removeAttribute("productName");
    }

%>
