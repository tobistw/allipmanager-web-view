import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITblFunkmessung } from 'app/shared/model/tbl-funkmessung.model';

type EntityResponseType = HttpResponse<ITblFunkmessung>;
type EntityArrayResponseType = HttpResponse<ITblFunkmessung[]>;

@Injectable({ providedIn: 'root' })
export class TblFunkmessungService {
    public resourceUrl = SERVER_API_URL + 'api/tbl-funkmessungs';

    constructor(private http: HttpClient) {}

    create(tblFunkmessung: ITblFunkmessung): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(tblFunkmessung);
        return this.http
            .post<ITblFunkmessung>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(tblFunkmessung: ITblFunkmessung): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(tblFunkmessung);
        return this.http
            .put<ITblFunkmessung>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITblFunkmessung>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITblFunkmessung[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(tblFunkmessung: ITblFunkmessung): ITblFunkmessung {
        const copy: ITblFunkmessung = Object.assign({}, tblFunkmessung, {
            funDatum:
                tblFunkmessung.funDatum != null && tblFunkmessung.funDatum.isValid() ? tblFunkmessung.funDatum.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.funDatum = res.body.funDatum != null ? moment(res.body.funDatum) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((tblFunkmessung: ITblFunkmessung) => {
                tblFunkmessung.funDatum = tblFunkmessung.funDatum != null ? moment(tblFunkmessung.funDatum) : null;
            });
        }
        return res;
    }
}
