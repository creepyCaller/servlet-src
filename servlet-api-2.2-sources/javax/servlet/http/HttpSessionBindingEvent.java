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

import java.util.EventObject;


/**
 *
 * Sent to an object that implements
 * {@link HttpSessionBindingListener} when the object is
 * bound to or unbound from the session.
 *
 * <p>The session binds the object by a call to
 * <code>HttpSession.putValue</code> and unbinds the object
 * by a call to <code>HttpSession.removeValue</code>.
 *
 *
 *
 * @author		Various
 * @version		$Version$
 *
 * @see 		HttpSession
 * @see 		HttpSessionBindingListener
 *
 */

public class HttpSessionBindingEvent extends EventObject {




    /* The name to which the object is being bound or unbound */

    private String name;
    
  

    /**
     *
     * Constructs an event that notifies an object that it
     * has been bound to or unbound from a session. 
     * To receive the event, the object must implement
     * {@link HttpSessionBindingListener}.
     *
     *
     *
     * @param session 	the session to which the object is bound or unbound
     *
     * @param name 	the name with which the object is bound or unbound
     *
     * @see			#getName
     * @see			#getSession
     *
     */

    public HttpSessionBindingEvent(HttpSession session, String name) {
	super(session);
	this.name = name;
    }
    
    
    
   
  
    
    /**
     *
     * Returns the name with which the object is bound to or
     * unbound from the session.
     *
     *
     * @return		a string specifying the name with which
     *			the object is bound to or unbound from
     *			the session
     *
     *
     */

    public String getName() {
	return name;
    }
    
    
  
  
    

    /**
     *
     * Returns the session to or from which the object is
     * bound or unbound.
     *
     * @return		the session to which the object is
     *			bound or from which the object is
     *			unbound
     *
     *
     *
     */
    
    public HttpSession getSession() {
	return (HttpSession) getSource();
    }
}







