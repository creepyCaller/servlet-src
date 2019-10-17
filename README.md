# Servlet源码与解析
目前的我理解的Servlet 生命周期：  
Request(POST/GET,HttpSession) --(HTTP)--> [Servlet(create, service(doGet, doPost), destroy)] --(HTTP)--> Response
