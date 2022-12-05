package network;

import java.io.*;
import java.nio.ByteBuffer;

public class Protocol implements Serializable {
    final static public int UNDEFINED = 0;
    final static public int NOTHING = 0;

    public static final int EXIT = -1;

    public static final int RES = 1;
    public static final int SUCCESS = 1;
    public static final int FAIL = 2;

    public static final int LOGIN_REQ = 1;
    public static final int LOGIN_RES = 2;

    public static final int REGISTER_REQ = 3;

    public static final int SIGN_UP = 10;

    public static final int STORE = 20;
    public static final int APPROVE_STORE = 21;
    public static final int REFUSE_STORE = 22;
    public static final int ORDERCNT_PER_STORE = 23;
    public static final int SALES_PER_STORE = 24;

    public static final int OWNER = 30;
    public static final int APPROVE_OWNER = 31;
    public static final int REFUSE_OWNER = 32;

    public static final int MENU = 40;
    public static final int APPROVE_MENU = 41;
    public static final int REFUSE_MENU = 42;
    public static final int MENU_OPTION = 43;
    public static final int ORDERCNT_PER_MENU = 44;
    public static final int SALES_PER_MENU = 45;

    public static final int ORDER = 50;
    public static final int APPROVE_ORDER = 51;
    public static final int REFUSE_ORDER = 52;
    public static final int ORDER_INFO = 53;

    public static final int REVIEW_AND_RATING = 60;
    public static final int REVIEW_REPLY = 61;

    public static final int INQUIRY_REQ = 4;

    public static final int INQUIRY_RES = 5;

    public static final int SETTING_REQ = 6;
    public static final int BUSINESS_HOURS = 25;

    public static final int UPDATE_REQ = 7;
    public static final int PRIVACY = 11;
    public static final int PASSWORD = 12;

    public static final int CANCEL_REQ = 8;

    public static final int LEN_TYPE = 1;
    public static final int LEN_CODE = 1;
    public static final int LEN_BODY_LENGTH = 4;
    public static final int LEN_HEADER = 6;
    private byte type;
    private byte code;
    private int bodyLength;
    private byte[] body;
    public Protocol() {
        this(UNDEFINED, NOTHING);
    }

    public Protocol(int type) {
        this(type, NOTHING);
    }

    public Protocol(int type, int code) {
        setType(type);
        setCode(code);
        setBodyLength(0);
    }

    public byte getType() {
        return type;
    }

    public void setType(int type) {
        this.type = (byte) type;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = (byte) code;
    }

    public int getBodyLength() {
        return bodyLength;
    }

    private void setBodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }

    public Object getBody() {
        return deserialize(body);
    }

    public void setBody(Object body) {
        byte[] serializedObject = serialize(body);
        this.body = serializedObject;
        setBodyLength(serializedObject.length);
    }

    public byte[] getPacket() {
        byte[] packet = new byte[LEN_HEADER + getBodyLength()];
        packet[0] = getType();
        packet[LEN_TYPE] = getCode();
        System.arraycopy(intToByte(getBodyLength()), 0, packet, LEN_TYPE + LEN_CODE, LEN_BODY_LENGTH);
        if (getBodyLength() > 0) {
            System.arraycopy(body, 0, packet, LEN_HEADER, getBodyLength());
        }
        return packet;
    }

    public void setPacketHeader(byte[] packet) {
        byte[] data;

        setType(packet[0]);
        setCode(packet[LEN_TYPE]);

        data = new byte[LEN_BODY_LENGTH];
        System.arraycopy(packet, LEN_TYPE + LEN_CODE, data, 0, LEN_BODY_LENGTH);
        setBodyLength(byteToInt(data));
    }

    public void setPacketBody(byte[] packet) {
        byte[] data;

        if (getBodyLength() > 0) {
            data = new byte[getBodyLength()];
            System.arraycopy(packet, 0, data, 0, getBodyLength());
            setBody(deserialize(data));
        }
    }

    private byte[] serialize(Object b) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(b);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Object deserialize(byte[] b) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object ob = ois.readObject();
            return ob;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] intToByte(int i) {
        return ByteBuffer.allocate(Integer.SIZE / 8).putInt(i).array();
    }

    private int byteToInt(byte[] b) {
        return ByteBuffer.wrap(b).getInt();
    }


}
