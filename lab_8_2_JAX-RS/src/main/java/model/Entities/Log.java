package model.Entities;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Entity(name = "LOGS")
public class Log {
    @XmlAttribute

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @XmlAttribute

    private String ip;
    @XmlAttribute

    private String log;

    public Log() {
    }

    public Log(String ip, String log) {
        this.ip = ip;
        this.log = log;
    }

    public String getIp() {
        return ip;
    }

    public String getLog() {
        return log;
    }
}
