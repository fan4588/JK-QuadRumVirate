package com.nephew.jk.utils;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import org.apache.commons.lang3.SerializationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 * 序列化实现工具
 */
public class HessianSerializer {

    /**
     * 序列化
     * @param object
     * @return
     * @throws SerializationException
     */
    public static byte[] serialize(Object object) throws SerializationException {
        if(object == null) {
            return null;
        } else {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Deflater def = new Deflater(9, false);
            DeflaterOutputStream dos = new DeflaterOutputStream(baos, def);
            Hessian2Output ho = null;

            try {
                ho = new Hessian2Output(dos);
                ho.writeObject(object);
            } catch (Exception e) {
                throw new SerializationException("Failed to encode date", e);
            } finally {
                if(ho != null) {
                    try {
                        ho.close();
                    } catch (IOException e) {

                    }
                }
                try {
                    dos.close();
                } catch (IOException e) {

                }
                def.end();
            }
            return baos.toByteArray();
        }
    }

    /**
     * 反序列化
     * @param encodedValue
     * @return
     * @throws SerializationException
     */
    public static Object deserialize(byte[] encodedValue) throws SerializationException {
        if (encodedValue == null || encodedValue.length <= 0) {
            return null;
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(encodedValue);
        Inflater inf = new Inflater(false);
        InflaterInputStream iis = new InflaterInputStream(bais, inf);
        Hessian2Input hi = null;

        Object obj;
        try {
            hi = new Hessian2Input(iis);
            obj = hi.readObject();
        } catch (Exception e) {
            throw new SerializationException("Failed to parse data", e);
        } finally {
            if(hi != null) {
                try {
                    hi.close();
                } catch (IOException e) {

                }
            }
            try {
                iis.close();
            } catch (IOException e) {

            }
            inf.end();
        }
        return obj;
    }
}