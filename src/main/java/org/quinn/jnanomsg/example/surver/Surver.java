package org.quinn.jnanomsg.example.surver;

import nanomsg.Nanomsg;
import nanomsg.Socket;


public class Surver extends Socket {
    public Surver() {
        super(Nanomsg.constants.AF_SP,((Integer)Nanomsg.symbols.get("NN_RESPONDENT")).intValue());
    }
}