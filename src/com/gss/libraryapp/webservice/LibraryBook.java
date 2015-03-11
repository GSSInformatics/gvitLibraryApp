package com.gss.libraryapp.webservice;

import java.util.List;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;

import com.gss.libraryapp.webservice.WS_Enums.SoapProtocolVersion;

public class LibraryBook {
    
    public String NAMESPACE ="http://tempuri.org/";
    public String url="http://192.168.88.29:8080/webservices/LibraryBook.asmx";
    public int timeOut = 60000;
    public IWsdl2CodeEvents eventHandler;
    public SoapProtocolVersion soapVersion;
    
    public LibraryBook(){}
    
    public LibraryBook(IWsdl2CodeEvents eventHandler)
    {
        this.eventHandler = eventHandler;
    }
    public LibraryBook(IWsdl2CodeEvents eventHandler,String url)
    {
        this.eventHandler = eventHandler;
        this.url = url;
    }
    public LibraryBook(IWsdl2CodeEvents eventHandler,String url,int timeOutInSeconds)
    {
        this.eventHandler = eventHandler;
        this.url = url;
        this.setTimeOut(timeOutInSeconds);
    }
    public void setTimeOut(int seconds){
        this.timeOut = seconds * 1000;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public void getBooksAsync() throws Exception{
        if (this.eventHandler == null)
            throw new Exception("Async Methods Requires IWsdl2CodeEvents");
        getBooksAsync(null);
    }
    
    public void getBooksAsync(final List<HeaderProperty> headers) throws Exception{
        
        new AsyncTask<Void, Void, VectorBook>(){
            @Override
            protected void onPreExecute() {
                eventHandler.Wsdl2CodeStartedRequest();
            };
            @Override
            protected VectorBook doInBackground(Void... params) {
                return getBooks(headers);
            }
            @Override
            protected void onPostExecute(VectorBook result)
            {
                eventHandler.Wsdl2CodeEndedRequest();
                if (result != null){
                    eventHandler.Wsdl2CodeFinished("getBooks", result);
                }
            }
        }.execute();
    }
    
    public VectorBook getBooks(){
        return getBooks(null);
    }
    
    public VectorBook getBooks(List<HeaderProperty> headers){
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("http://tempuri.org/","getBooks");
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url,timeOut);
        try{
            if (headers!=null){
                httpTransport.call("http://tempuri.org/getBooks", soapEnvelope,headers);
            }else{
                httpTransport.call("http://tempuri.org/getBooks", soapEnvelope);
            }
            Object retObj = soapEnvelope.bodyIn;
            if (retObj instanceof SoapFault){
                SoapFault fault = (SoapFault)retObj;
                Exception ex = new Exception(fault.faultstring);
                if (eventHandler != null)
                    eventHandler.Wsdl2CodeFinishedWithException(ex);
            }else{
                SoapObject result=(SoapObject)retObj;
                if (result.getPropertyCount() > 0){
                    Object obj = result.getProperty(0);
                    SoapObject j = (SoapObject)obj;
                    VectorBook resultVariable = new VectorBook(j);
                    return resultVariable;
                }
            }
        }catch (Exception e) {
            if (eventHandler != null)
                eventHandler.Wsdl2CodeFinishedWithException(e);
            e.printStackTrace();
        }
        return null;
    }
    
}
