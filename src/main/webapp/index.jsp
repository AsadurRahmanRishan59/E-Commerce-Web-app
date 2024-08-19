<%@page import="com.mycompany.ecommerce.helper.Helper"%>
<%@page import="com.mycompany.ecommerce.entities.Category"%>
<%@page import="com.mycompany.ecommerce.dao.CategoryDao"%>
<%@page import="com.mycompany.ecommerce.entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.ecommerce.dao.ProductDao"%>
<%@page import="com.mycompany.ecommerce.helper.FactoryProvider"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>

        <%@include file="components/common_css_js.jsp" %>

    </head>
    <body>
        <%@include file="components/navbar.jsp" %>

        <div class="row mt-3 mx-2">

            <!--show categories-->
            <%  CategoryDao categoryDao = new CategoryDao(FactoryProvider.getFactory());
                List<Category> categories = categoryDao.getCategories();
            %>

            <div class="col-md-2">
                <div class="list-group mt-4">
                    <a href="#" class="list-group-item list-group-item-action active">All Categories</a>
                    <%for (Category category : categories) {
                    %>

                    <a href="#" class="list-group-item list-group-item-action"><%=category.getCategoryTitle()%></a>
                    <%
                        }
                    %>
                </div>
            </div>

            <!--show products-->
            <%  ProductDao productDao = new ProductDao(FactoryProvider.getFactory());
                List<Product> products = productDao.getAllProducts();
            %>
            <div class="col-md-8">

                <!--row-->
                <div class="row mt-4">
                    <div class="col-md-12">
                        <div class="card-columns">
                            <%
                                for (Product product : products) {
                            %>

                            <div class="card" style="width: 18rem;">
                                <img class="card-img-top" src="img/products/<%= product.getProductPic() %>" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title"><%= product.getpName()%></h5>
                                    <p class="card-text"><%= Helper.get10Words(product.getpDescription())%></p>
                                </div>
                                <div class="card-footer">
                                    <button class="btn nav-color text-white">Add to Cart</button>
                                    <button class="btn btn-outline-primary"> &#2547 <%= product.getpPrice() %></button>
                                </div>   
                            </div>

                            <%
                                }
                            %>
                        </div>
                    </div>

                </div>

            </div>
        </div>

    </body>
</html>
