import { Moment } from 'moment';

export interface IDashboard {
    id?: number;
    zeitstempel?: Moment;
    titel?: string;
    nachricht?: string;
}

export class Dashboard implements IDashboard {
    constructor(public id?: number, public zeitstempel?: Moment, public titel?: string, public nachricht?: string) {}
}
