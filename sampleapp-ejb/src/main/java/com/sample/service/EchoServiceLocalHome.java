/*
 * Generated by XDoclet - Do not edit!
 */
package com.sample.service;

/**
 * Local home interface for EchoService.
 * @generated 
 */
public interface EchoServiceLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/EchoServiceLocal";
   public static final String JNDI_NAME="ejb/EchoServiceLocal";

   public com.sample.service.EchoServiceLocal create()
      throws javax.ejb.CreateException;

}
