<%-- 
    Document   : registerImag
    Created on : 09-oct-2022, 13:01:59
    Author     : drago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Register image jsp</h1>
        <form action="registerImg" method="post" enctype="multipart/form-data">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title"><br>
            <label for="description">Description:</label>
            <input type="text" id="description" name="description"><br>
            <label for="keywords">Keywords</label>
            <input type="text" id="keywords" name="keywords"><br>
            <label for="author">Author</label>
            <input type="text" id="author" name="author"><br>
            <label for="creator">Creator</label>
            <input type="text" id="creator" name="creator"><br>
            <label for="capturingDate">Capturing date</label>
            <input type="date" id="capturingDate" name="capturingDate" value="1-1-2010" min="2000-1-1" max="2022-12-12"><br>
            <label for="fileName">File name (with extension)</label>
            <input type="text" id="fileName" name="fileName" ><br>
            <label for="encr">Encript file:  </label>
            <input type="checkbox" id="encrypt" name="encrypt" >
            <input type="file" name="file"/>
            <input type="submit" value="Upload"/>
            <label for="encrypt"> yes</label>
        </form>
    </body>
</html>
