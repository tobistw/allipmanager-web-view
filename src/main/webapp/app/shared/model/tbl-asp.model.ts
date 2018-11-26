import { ITblVertrag } from 'app/shared/model//tbl-vertrag.model';
import { ITndSiemens } from 'app/shared/model//tnd-siemens.model';

export interface ITblAsp {
    id?: number;
    aspId?: number;
    aspName?: string;
    aspRolle?: string;
    aspTel1?: string;
    aspMobil1?: string;
    aspEmail?: string;
    aspObjektNrRef?: string;
    aspBemerkung?: string;
    kunde?: ITblVertrag;
    siemens?: ITndSiemens;
}

export class TblAsp implements ITblAsp {
    constructor(
        public id?: number,
        public aspId?: number,
        public aspName?: string,
        public aspRolle?: string,
        public aspTel1?: string,
        public aspMobil1?: string,
        public aspEmail?: string,
        public aspObjektNrRef?: string,
        public aspBemerkung?: string,
        public kunde?: ITblVertrag,
        public siemens?: ITndSiemens
    ) {}
}
