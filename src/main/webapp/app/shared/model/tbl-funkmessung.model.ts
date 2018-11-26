import { Moment } from 'moment';
import { ITblTermine } from 'app/shared/model//tbl-termine.model';

export interface ITblFunkmessung {
    id?: number;
    funId?: number;
    funObjektNr?: string;
    funDatum?: Moment;
    funD1?: string;
    funD2?: string;
    funO2?: string;
    funEplus?: string;
    funZusatz?: string;
    funKabel?: string;
    funBemerkung?: string;
    funName?: string;
    tblTermine?: ITblTermine;
}

export class TblFunkmessung implements ITblFunkmessung {
    constructor(
        public id?: number,
        public funId?: number,
        public funObjektNr?: string,
        public funDatum?: Moment,
        public funD1?: string,
        public funD2?: string,
        public funO2?: string,
        public funEplus?: string,
        public funZusatz?: string,
        public funKabel?: string,
        public funBemerkung?: string,
        public funName?: string,
        public tblTermine?: ITblTermine
    ) {}
}
