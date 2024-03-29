/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:  
 *       "This product includes software developed by the 
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written 
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * ====================================================================
 *
 * This source code implements specifications defined by the Java
 * Community Process. In order to remain compliant with the specification
 * DO NOT add / change / or delete method signatures!
 */ 


package javax.servlet.http;

import javax.servlet.ServletRequest;
import java.util.Enumeration;

/**
 *
 * Extends the {@link javax.servlet.ServletRequest} interface
 * to provide request information for HTTP servlets. 
 *
 * <p>The servlet container creates an <code>HttpServletRequest</code> 
 * object and passes it as an argument to the servlet's service
 * methods (<code>doGet</code>, <code>doPost</code>, etc).
 *
 *
 * @author 	Various
 * @version	$Version$
 *
 *
 */

public interface HttpServletRequest extends ServletRequest {



    /**
     * Returns the name of the authentication scheme used to protect
     * the servlet, for example, "BASIC" or "SSL," or <code>null</code>
     * if the servlet was not protected. 
     *
     * <p>Same as the value of the CGI variable AUTH_TYPE.
     *
     *
     * @return		a <code>String</code> specifying the name of
     *			the authentication scheme, or
     *			<code>null</code> if the request was not
     *			authenticated
     *
     */
   
    public String getAuthType();
    
   
    

    /**
     *
     * Returns an array containing all of the <code>Cookie</code>
     * objects the client sent with this request.
     * This method returns <code>null</code> if no cookies were sent.
     *
     * @return		an array of all the <code>Cookies</code>
     *			included with this request, or <code>null</code>
     *			if the request has no cookies
     *
     *
     */

    public Cookie[] getCookies();
    
    
    

    /**
     *
     * Returns the value of the specified request header
     * as a <code>long</code> value that represents a 
     * <code>Date</code> object. Use this method with
     * headers that contain dates, such as
     * <code>If-Modified-Since</code>. 
     *
     * <p>The date is returned as
     * the number of milliseconds since January 1, 1970 GMT.
     * The header name is case insensitive.
     *
     * <p>If the request did not have a header of the
     * specified name, this method returns -1. If the header
     * can't be converted to a date, the method throws
     * an <code>IllegalArgumentException</code>.
     *
     * @param name		a <code>String</code> specifying the
     *				name of the header
     *
     * @return			a <code>long</code> value
     *				representing the date specified
     *				in the header expressed as
     *				the number of milliseconds
     *				since January 1, 1970 GMT,
     *				or -1 if the named header
     *				was not included with the
     *				reqest
     *
     * @exception	IllegalArgumentException	If the header value
     *							can't be converted
     *							to a date
     *
     */

    public long getDateHeader(String name);
    
    
    

    /**
     *
     * Returns the value of the specified request header
     * as a <code>String</code>. If the request did not include a header
     * of the specified name, this method returns <code>null</code>.
     * The header name is case insensitive. You can use
     * this method with any request header.
     *
     * @param name		a <code>String</code> specifying the
     *				header name
     *
     * @return			a <code>String</code> containing the
     *				value of the requested
     *				header, or <code>null</code>
     *				if the request does not
     *				have a header of that name
     *
     */			

    public String getHeader(String name); 




    /**
     *
     * Returns all the values of the specified request header
     * as an <code>Enumeration</code> of <code>String</code> objects.
     *
     * <p>Some headers, such as <code>Accept-Language</code> can be sent
     * by clients as several headers each with a different value rather than
     * sending the header as a comma separated list.
     *
     * <p>If the request did not include any headers
     * of the specified name, this method returns an empty
     * <code>Enumeration</code>.
     * The header name is case insensitive. You can use
     * this method with any request header.
     *
     * @param name		a <code>String</code> specifying the
     *				header name
     *
     * @return			a <code>Enumeration</code> containing the
     *				values of the requested
     *				header, or <code>null</code>
     *				if the request does not
     *				have any headers of that name
     *
     */			

    public Enumeration getHeaders(String name); 
    
    
    
    

    /**
     *
     * Returns an enumeration of all the header names
     * this request contains. If the request has no
     * headers, this method returns an empty enumeration.
     *
     * <p>Some servlet containers do not allow do not allow
     * servlets to access headers using this method, in
     * which case this method returns <code>null</code>
     *
     * @return			an enumeration of all the
     *				header names sent with this
     *				request; if the request has
     *				no headers, an empty enumeration;
     *				if the servlet container does not
     *				allow servlets to use this method,
     *				<code>null</code>
     *
     */

    public Enumeration getHeaderNames();
    
    
    

    /**
     *
     * Returns the value of the specified request header
     * as an <code>int</code>. If the request does not have a header
     * of the specified name, this method returns -1. If the
     * header cannot be converted to an integer, this method
     * throws a <code>NumberFormatException</code>.
     *
     * <p>The header name is case insensitive.
     *
     * @param name		a <code>String</code> specifying the name
     *				of a request header
     *
     * @return			an integer expressing the value 
     * 				of the request header or -1
     *				if the request doesn't have a
     *				header of this name
     *
     * @exception	NumberFormatException		If the header value
     *							can't be converted
     *							to an <code>int</code>
     */

    public int getIntHeader(String name);
    
    
    

    /**
     *
     * Returns the name of the HTTP method with which this 
     * request was made, for example, GET, POST, or PUT.
     * Same as the value of the CGI variable REQUEST_METHOD.
     *
     * @return			a <code>String</code> 
     *				specifying the name
     *				of the method with which
     *				this request was made
     *
     */
 
    public String getMethod();
    
    
    

    /**
     *
     * Returns any extra path information associated with
     * the URL the client sent when it made this request.
     * The extra path information follows the servlet path
     * but precedes the query string.
     * This method returns <code>null</code> if there
     * was no extra path information.
     *
     * <p>Same as the value of the CGI variable PATH_INFO.
     *
     *
     * @return		a <code>String</code> specifying 
     *			extra path information that comes
     *			after the servlet path but before
     *			the query string in the request URL;
     *			or <code>null</code> if the URL does not have
     *			any extra path information
     *
     */
     
    public String getPathInfo();
    

 

    /**
     *
     * Returns any extra path information after the servlet name
     * but before the query string, and translates it to a real
     * path. Same as the value of the CGI variable PATH_TRANSLATED.
     *
     * <p>If the URL does not have any extra path information,
     * this method returns <code>null</code>.
     *
     *
     * @return		a <code>String</code> specifying the
     *			real path, or <code>null</code> if
     *			the URL does not have any extra path
     *			information
     *
     *
     */

    public String getPathTranslated();
    

 

    /**
     *
     * Returns the portion of the request URI that indicates the context
     * of the request.  The context path always comes first in a request
     * URI.  The path starts with a "/" character but does not end with a "/"
     * character.  For servlets in the default (root) context, this method
     * returns "".
     *
     *
     * @return		a <code>String</code> specifying the
     *			portion of the request URI that indicates the context
     *			of the request
     *
     *
     */

    public String getContextPath();
    
    
    

    /**
     *
     * Returns the query string that is contained in the request
     * URL after the path. This method returns <code>null</code>
     * if the URL does not have a query string. Same as the value
     * of the CGI variable QUERY_STRING.
     *
     * @return		a <code>String</code> containing the query
     *			string or <code>null</code> if the URL 
     *			contains no query string
     *
     */

    public String getQueryString();
    
    
    

    /**
     *
     * Returns the login of the user making this request, if the
     * user has been authenticated, or <code>null</code> if the user 
     * has not been authenticated.
     * Whether the user name is sent with each subsequent request
     * depends on the browser and type of authentication. Same as the 
     * value of the CGI variable REMOTE_USER.
     *
     * @return		a <code>String</code> specifying the login
     *			of the user making this request, or <code>null</code
     *			if the user login is not known
     *
     */

    public String getRemoteUser();
    
    
    

    /**
     *
     * Returns a boolean indicating whether the authenticated user is included
     * in the specified logical "role".  Roles and role membership can be
     * defined using deployment descriptors.  If the user has not been
     * authenticated, the method returns <code>false</code>.
     *
     * @param role		a <code>String</code> specifying the name
     *				of the role
     *
     * @return		a <code>boolean</code> indicating whether
     *			the user making this request belongs to a given role;
     *			<code>false</code> if the user has not been 
     *			authenticated
     *
     */

    public boolean isUserInRole(String role);
    
    
    

    /**
     *
     * Returns a <code>java.security.Principal</code> object containing
     * the name of the current authenticated user. If the user has not been
     * authenticated, the method returns <code>null</code>.
     *
     * @return		a <code>java.security.Principal</code> containing
     *			the name of the user making this request;
     *			<code>null</code> if the user has not been 
     *			authenticated
     *
     */

    public java.security.Principal getUserPrincipal();
    
    
    

    /**
     *
     * Returns the session ID specified by the client. This may
     * not be the same as the ID of the actual session in use.
     * For example, if the request specified an old (expired)
     * session ID and the server has started a new session, this
     * method gets a new session with a new ID. If the request
     * did not specify a session ID, this method returns
     * <code>null</code>.
     *
     *
     * @return		a <code>String</code> specifying the session
     *			ID, or <code>null</code> if the request did
     *			not specify a session ID
     *
     * @see		#isRequestedSessionIdValid
     *
     */

    public String getRequestedSessionId();
    
    
    
    
    /**
     *
     * Returns the part of this request's URL from the protocol
     * name up to the query string in the first line of the HTTP request.
     * For example:
     *
     * <blockquote>
     * <table>
     * <tr align=left><th>First line of HTTP request<th>
     * <th>Returned Value
     * <tr><td>POST /some/path.html HTTP/1.1<td><td>/some/path.html
     * <tr><td>GET http://foo.bar/a.html HTTP/1.0
     * <td><td>http://foo.bar/a.html
     * <tr><td>HEAD /xyz?a=b HTTP/1.1<td><td>/xyz
     * </table>
     * </blockquote>
     *
     * <p>To reconstruct an URL with a scheme and host, use
     * {@link HttpUtils#getRequestURL}.
     *
     * @return		a <code>String</code> containing
     *			the part of the URL from the 
     *			protocol name up to the query string
     *
     * @see		HttpUtils#getRequestURL
     *
     */

    public String getRequestURI();
    
    
    

    /**
     *
     * Returns the part of this request's URL that calls
     * the servlet. This includes either the servlet name or
     * a path to the servlet, but does not include any extra
     * path information or a query string. Same as the value 
     * of the CGI variable SCRIPT_NAME.
     *
     *
     * @return		a <code>String</code> containing
     *			the name or path of the servlet being
     *			called, as specified in the request URL 
     *
     *
     */

    public String getServletPath();
    
    
    

    /**
     *
     * Returns the current <code>HttpSession</code>
     * associated with this request or, if if there is no
     * current session and <code>create</code> is true, returns 
     * a new session.
     *
     * <p>If <code>create</code> is <code>false</code>
     * and the request has no valid <code>HttpSession</code>,
     * this method returns <code>null</code>.
     *
     * <p>To make sure the session is properly maintained,
     * you must call this method before 
     * the response is committed.
     *
     *
     *
     *
     * @param		<code>true</code> to create
     *			a new session for this request if necessary; 
     *			<code>false</code> to return <code>null</code>
     *			if there's no current session
     *			
     *
     * @return 		the <code>HttpSession</code> associated 
     *			with this request or <code>null</code> if
     * 			<code>create</code> is <code>false</code>
     *			and the request has no valid session
     *
     * @see	#getSession()
     *
     *
     */

    public HttpSession getSession(boolean create);
    
    
    
   

    /**
     *
     * Returns the current session associated with this request,
     * or if the request does not have a session, creates one.
     * 
     * @return		the <code>HttpSession</code> associated
     *			with this request
     *
     * @see	#getSession(boolean)
     *
     */

    public HttpSession getSession();
    
    
    
    
    

    /**
     *
     * Checks whether the requested session ID is still valid.
     *
     * @return			<code>true</code> if this
     *				request has an id for a valid session
     *				in the current session context;
     *				<code>false</code> otherwise
     *
     * @see			#getRequestedSessionId
     * @see			#getSession
     * @see			HttpSessionContext
     *
     */

    public boolean isRequestedSessionIdValid();
    
    
    

    /**
     *
     * Checks whether the requested session ID came in as a cookie.
     *
     * @return			<code>true</code> if the session ID
     *				came in as a
     *				cookie; otherwise, <code>false</code>
     *
     *
     * @see			#getSession
     *
     */ 

    public boolean isRequestedSessionIdFromCookie();
    
    
    

    /**
     *
     * Checks whether the requested session ID came in as part of the 
     * request URL.
     *
     * @return			<code>true</code> if the session ID
     *				came in as part of a URL; otherwise,
     *				<code>false</code>
     *
     *
     * @see			#getSession
     *
     */
    
    public boolean isRequestedSessionIdFromURL();
    
    
    
    
    
    /**
     *
     * @deprecated		As of Version 2.1 of the Java Servlet
     *				API, use {@link #isRequestedSessionIdFromURL}
     *				instead.
     *
     */

    public boolean isRequestedSessionIdFromUrl();


    
}
