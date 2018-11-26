import { Moment } from 'moment';
import { ITblTermine } from 'app/shared/model//tbl-termine.model';
import { ITblAsp } from 'app/shared/model//tbl-asp.model';

export interface ITndSiemens {
    id?: number;
    tndObjNr?: string;
    tndTeilObjNr?: string;
    tndWarten?: Moment;
    tndLeitungsNrAlt?: string;
    tndLeitungsNrNeu?: string;
    tndGSMNr?: string;
    tndGSMNr2?: string;
    tndWartungBMA?: string;
    tndSiSe?: string;
    tndLogin?: string;
    tndPasswort?: string;
    tndIP?: string;
    tndTelekomNr?: number;
    tndLeitungsNrPseudo?: number;
    tndDslFrist?: Moment;
    termins?: ITblTermine[];
    kontakts?: ITblAsp[];
}

export class TndSiemens implements ITndSiemens {
    constructor(
        public id?: number,
        public tndObjNr?: string,
        public tndTeilObjNr?: string,
        public tndWarten?: Moment,
        public tndLeitungsNrAlt?: string,
        public tndLeitungsNrNeu?: string,
        public tndGSMNr?: string,
        public tndGSMNr2?: string,
        public tndWartungBMA?: string,
        public tndSiSe?: string,
        public tndLogin?: string,
        public tndPasswort?: string,
        public tndIP?: string,
        public tndTelekomNr?: number,
        public tndLeitungsNrPseudo?: number,
        public tndDslFrist?: Moment,
        public termins?: ITblTermine[],
        public kontakts?: ITblAsp[]
    ) {}
}
