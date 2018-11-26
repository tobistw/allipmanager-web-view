import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { ITblTermine } from 'app/shared/model//tbl-termine.model';

export interface IAbnahmeprotokoll {
    id?: number;
    objektNummer?: string;
    datumLeistung?: Moment;
    firma?: string;
    kundeAsp?: string;
    firmaAsp?: string;
    sise?: boolean;
    zusatzleistungen?: string;
    antenne?: string;
    gprsSignal?: string;
    alarmTest?: boolean;
    alarmBemerkung?: string;
    ort?: string;
    datum?: Moment;
    abnProtokollContentType?: string;
    abnProtokoll?: any;
    user?: IUser;
    abnahmeprotokoll?: ITblTermine;
}

export class Abnahmeprotokoll implements IAbnahmeprotokoll {
    constructor(
        public id?: number,
        public objektNummer?: string,
        public datumLeistung?: Moment,
        public firma?: string,
        public kundeAsp?: string,
        public firmaAsp?: string,
        public sise?: boolean,
        public zusatzleistungen?: string,
        public antenne?: string,
        public gprsSignal?: string,
        public alarmTest?: boolean,
        public alarmBemerkung?: string,
        public ort?: string,
        public datum?: Moment,
        public abnProtokollContentType?: string,
        public abnProtokoll?: any,
        public user?: IUser,
        public abnahmeprotokoll?: ITblTermine
    ) {
        this.sise = this.sise || false;
        this.alarmTest = this.alarmTest || false;
    }
}
