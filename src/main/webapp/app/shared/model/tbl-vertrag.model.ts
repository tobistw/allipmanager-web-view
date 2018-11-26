import { ITblTermine } from 'app/shared/model//tbl-termine.model';
import { ITblAsp } from 'app/shared/model//tbl-asp.model';

export interface ITblVertrag {
    id?: number;
    verHv?: string;
    verEquipmentNr?: string;
    verObjektNr?: string;
    verTeilmelder?: string;
    verVpName?: string;
    verVpName2?: string;
    verVpStrasse?: string;
    verVpPlz?: string;
    verVpOrt?: string;
    verObjName?: string;
    verObjStrasse?: string;
    verObjPlz?: string;
    verObjOrt?: string;
    verSiseVertrag?: string;
    verSiseTyp?: string;
    verKonzId?: string;
    verWartung?: string;
    verMarkieren?: boolean;
    verDummy?: boolean;
    termines?: ITblTermine[];
    kontakts?: ITblAsp[];
}

export class TblVertrag implements ITblVertrag {
    constructor(
        public id?: number,
        public verHv?: string,
        public verEquipmentNr?: string,
        public verObjektNr?: string,
        public verTeilmelder?: string,
        public verVpName?: string,
        public verVpName2?: string,
        public verVpStrasse?: string,
        public verVpPlz?: string,
        public verVpOrt?: string,
        public verObjName?: string,
        public verObjStrasse?: string,
        public verObjPlz?: string,
        public verObjOrt?: string,
        public verSiseVertrag?: string,
        public verSiseTyp?: string,
        public verKonzId?: string,
        public verWartung?: string,
        public verMarkieren?: boolean,
        public verDummy?: boolean,
        public termines?: ITblTermine[],
        public kontakts?: ITblAsp[]
    ) {
        this.verMarkieren = this.verMarkieren || false;
        this.verDummy = this.verDummy || false;
    }
}
