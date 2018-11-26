import { Moment } from 'moment';
import { ITblFunkmessung } from 'app/shared/model//tbl-funkmessung.model';
import { IUser } from 'app/core/user/user.model';
import { ITblVertrag } from 'app/shared/model//tbl-vertrag.model';
import { ITndSiemens } from 'app/shared/model//tnd-siemens.model';

export interface ITblTermine {
    id?: number;
    terTermianId?: number;
    terObjektNr?: string;
    terPerNrRef?: string;
    terIbsDatum?: Moment;
    terIbsUhrzeit?: string;
    terDatumNeu?: Moment;
    terBemerkung?: string;
    terAntennen?: boolean;
    terMontiert?: boolean;
    terIbsProvisorium?: boolean;
    terKonzId?: string;
    terStatus?: string;
    terStatusProtokoll?: string;
    terKundenInfoTyp?: string;
    terKundeInformiert?: boolean;
    terKundeBestaetigt?: boolean;
    terKundeAntenneErhalten?: boolean;
    terSiemensInformiert?: boolean;
    terDslFrist?: Moment;
    terTermintyp?: string;
    terFunkmessung?: boolean;
    terKlaerung?: boolean;
    terAbgesagt?: boolean;
    terHinweis?: boolean;
    terSimImGeraet?: boolean;
    funkmessungs?: ITblFunkmessung[];
    user?: IUser;
    vertrag?: ITblVertrag;
    siemens?: ITndSiemens;
}

export class TblTermine implements ITblTermine {
    constructor(
        public id?: number,
        public terTermianId?: number,
        public terObjektNr?: string,
        public terPerNrRef?: string,
        public terIbsDatum?: Moment,
        public terIbsUhrzeit?: string,
        public terDatumNeu?: Moment,
        public terBemerkung?: string,
        public terAntennen?: boolean,
        public terMontiert?: boolean,
        public terIbsProvisorium?: boolean,
        public terKonzId?: string,
        public terStatus?: string,
        public terStatusProtokoll?: string,
        public terKundenInfoTyp?: string,
        public terKundeInformiert?: boolean,
        public terKundeBestaetigt?: boolean,
        public terKundeAntenneErhalten?: boolean,
        public terSiemensInformiert?: boolean,
        public terDslFrist?: Moment,
        public terTermintyp?: string,
        public terFunkmessung?: boolean,
        public terKlaerung?: boolean,
        public terAbgesagt?: boolean,
        public terHinweis?: boolean,
        public terSimImGeraet?: boolean,
        public funkmessungs?: ITblFunkmessung[],
        public user?: IUser,
        public vertrag?: ITblVertrag,
        public siemens?: ITndSiemens
    ) {
        this.terAntennen = this.terAntennen || false;
        this.terMontiert = this.terMontiert || false;
        this.terIbsProvisorium = this.terIbsProvisorium || false;
        this.terKundeInformiert = this.terKundeInformiert || false;
        this.terKundeBestaetigt = this.terKundeBestaetigt || false;
        this.terKundeAntenneErhalten = this.terKundeAntenneErhalten || false;
        this.terSiemensInformiert = this.terSiemensInformiert || false;
        this.terFunkmessung = this.terFunkmessung || false;
        this.terKlaerung = this.terKlaerung || false;
        this.terAbgesagt = this.terAbgesagt || false;
        this.terHinweis = this.terHinweis || false;
        this.terSimImGeraet = this.terSimImGeraet || false;
    }
}
