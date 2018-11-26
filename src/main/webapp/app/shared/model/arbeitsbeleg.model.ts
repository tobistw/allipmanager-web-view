import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { ITblTermine } from 'app/shared/model//tbl-termine.model';

export const enum Belegstatus {
    OFFEN = 'OFFEN',
    FERTIG = 'FERTIG',
    ABBRUCH = 'ABBRUCH'
}

export interface IArbeitsbeleg {
    id?: number;
    objektNummer?: string;
    datumLeistung?: Moment;
    status?: Belegstatus;
    arbeitsbelegContentType?: string;
    arbeitsbeleg?: any;
    user?: IUser;
    termin?: ITblTermine;
}

export class Arbeitsbeleg implements IArbeitsbeleg {
    constructor(
        public id?: number,
        public objektNummer?: string,
        public datumLeistung?: Moment,
        public status?: Belegstatus,
        public arbeitsbelegContentType?: string,
        public arbeitsbeleg?: any,
        public user?: IUser,
        public termin?: ITblTermine
    ) {}
}
