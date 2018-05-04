package org.quinn.jnanomsg.example.surver;

import nanomsg.Nanomsg;
import nanomsg.Socket;


public class Respondent extends  Socket{
    public Respondent() {
        super(Nanomsg.constants.AF_SP,((Integer)Nanomsg.symbols.get("NN_RESPONDENT")).intValue());
    }
}
