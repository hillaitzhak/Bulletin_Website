<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Safe Page</title>
</head>
<body onLoad="redirect()">



</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
function redirect () {
    setTimeout("go_now()",10);
}

function go_now () {
    window.location.href = "/SecureDev/safePage";
}

</SCRIPT>
