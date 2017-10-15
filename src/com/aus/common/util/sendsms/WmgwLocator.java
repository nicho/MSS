package com.aus.common.util.sendsms;

import org.apache.commons.lang3.StringUtils;

public class WmgwLocator extends org.apache.axis.client.Service implements org.tempuri.Wmgw {

    public WmgwLocator() {
    }


    public WmgwLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WmgwLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for wmgwSoap
//    private java.lang.String wmgwSoap_address = "http://ws.montnets.com:9002/MWGate/wmgw.asmx";
    private java.lang.String wmgwSoap_address = "http://61.145.229.29:9002/MWGate/wmgw.asmx";
    public java.lang.String getwmgwSoapAddress() {
        return wmgwSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String wmgwSoapWSDDServiceName = "wmgwSoap";

    public java.lang.String getwmgwSoapWSDDServiceName() {
        return wmgwSoapWSDDServiceName;
    }

    public void setwmgwSoapWSDDServiceName(java.lang.String name) {
        wmgwSoapWSDDServiceName = name;
    }

    public org.tempuri.WmgwSoap getwmgwSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(wmgwSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getwmgwSoap(endpoint);
    }
    public org.tempuri.WmgwSoap getwmgwSoapServer(String serverHost,String serverPort) throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
         try {
        	 if(!StringUtils.isEmpty(serverHost) && !StringUtils.isEmpty(serverPort))
        	 {
        		  endpoint = new java.net.URL("http://"+serverHost+":"+serverPort+"/MWGate/wmgw.asmx");
        	 }else
        	 {
        		 endpoint = new java.net.URL(wmgwSoap_address);
        	 }
           
         }
         catch (java.net.MalformedURLException e) {
             throw new javax.xml.rpc.ServiceException(e);
         }
         return getwmgwSoap(endpoint);
     }
    public org.tempuri.WmgwSoap getwmgwSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.WmgwSoapStub _stub = new org.tempuri.WmgwSoapStub(portAddress, this);
            _stub.setPortName(getwmgwSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setwmgwSoapEndpointAddress(java.lang.String address) {
        wmgwSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.WmgwSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.WmgwSoapStub _stub = new org.tempuri.WmgwSoapStub(new java.net.URL(wmgwSoap_address), this);
                _stub.setPortName(getwmgwSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Exception t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("wmgwSoap".equals(inputPortName)) {
            return getwmgwSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "wmgw");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "wmgwSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("wmgwSoap".equals(portName)) {
            setwmgwSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
