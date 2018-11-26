import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAbnahmeprotokoll } from 'app/shared/model/abnahmeprotokoll.model';

type EntityResponseType = HttpResponse<IAbnahmeprotokoll>;
type EntityArrayResponseType = HttpResponse<IAbnahmeprotokoll[]>;

@Injectable({ providedIn: 'root' })
export class AbnahmeprotokollService {
    public resourceUrl = SERVER_API_URL + 'api/abnahmeprotokolls';

    constructor(private http: HttpClient) {}

    create(abnahmeprotokoll: IAbnahmeprotokoll): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(abnahmeprotokoll);
        return this.http
            .post<IAbnahmeprotokoll>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(abnahmeprotokoll: IAbnahmeprotokoll): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(abnahmeprotokoll);
        return this.http
            .put<IAbnahmeprotokoll>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAbnahmeprotokoll>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAbnahmeprotokoll[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(abnahmeprotokoll: IAbnahmeprotokoll): IAbnahmeprotokoll {
        const copy: IAbnahmeprotokoll = Object.assign({}, abnahmeprotokoll, {
            datumLeistung:
                abnahmeprotokoll.datumLeistung != null && abnahmeprotokoll.datumLeistung.isValid()
                    ? abnahmeprotokoll.datumLeistung.format(DATE_FORMAT)
                    : null,
            datum: abnahmeprotokoll.datum != null && abnahmeprotokoll.datum.isValid() ? abnahmeprotokoll.datum.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.datumLeistung = res.body.datumLeistung != null ? moment(res.body.datumLeistung) : null;
            res.body.datum = res.body.datum != null ? moment(res.body.datum) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((abnahmeprotokoll: IAbnahmeprotokoll) => {
                abnahmeprotokoll.datumLeistung = abnahmeprotokoll.datumLeistung != null ? moment(abnahmeprotokoll.datumLeistung) : null;
                abnahmeprotokoll.datum = abnahmeprotokoll.datum != null ? moment(abnahmeprotokoll.datum) : null;
            });
        }
        return res;
    }
}
