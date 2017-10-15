package com.aus.common.util.sendsms;

public interface Wmgw extends javax.xml.rpc.Service {
    public java.lang.String getwmgwSoapAddress();

    public org.tempuri.WmgwSoap getwmgwSoap() throws javax.xml.rpc.ServiceException;

    public org.tempuri.WmgwSoap getwmgwSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
