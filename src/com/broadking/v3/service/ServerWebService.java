package com.broadking.v3.service;

import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Base64;

import com.broadking.v3.AppConfig;
import com.broadking.v3.util.LogUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * WebService 请求
 */
public class ServerWebService {

	public static Object invoke(String invokeMethodName, TypeToken<?> returnType,
			Map<String, Object> parameter) {
		Object result = null;
		LogUtil.d("请求方法：" + invokeMethodName);
		String str = soapRequest(invokeMethodName, parameter);
		LogUtil.d("返回数据 : " + str);
		try {
			if (str != null) {
				if ("String".equals(returnType.getRawType().getSimpleName())) {
					result = str;
				} else {
					Gson gson = new GsonBuilder().setDateFormat(
							"yyyy-MM-dd HH:mm:ss").create();
					result = gson.fromJson(str, returnType.getType());
				}
			}

		} catch (Exception e) {
			LogUtil.e("json exception：" + e.toString());
		}
		return result;
	}


	private static String soapRequest(String methodName,
			Map<String, Object> parameter) {
		String response = null;
		try {
			SoapObject request = new SoapObject(AppConfig.NAMESPACE, methodName);
			LogUtil.d("请求参数---->>>");
			for (String keyStr : parameter.keySet()) {
				LogUtil.i("params name:" + keyStr + "---value:"
						+ parameter.get(keyStr));
				request.addProperty(keyStr, parameter.get(keyStr));
			}
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.setOutputSoapObject(request);
			HttpTransportSE httpTransport = new HttpTransportSE(AppConfig.URL);
			httpTransport.call(null, envelope);
			SoapObject resultRequestSOAP = (SoapObject) envelope.bodyIn;
			response = resultRequestSOAP.getProperty("return").toString();
			response = new String(Base64.decode(response, Base64.DEFAULT));
		} catch (Exception e) {
			LogUtil.e("方法 : " + methodName + "\n异常 : " + e.toString());
		}
		return response;

	}
}
