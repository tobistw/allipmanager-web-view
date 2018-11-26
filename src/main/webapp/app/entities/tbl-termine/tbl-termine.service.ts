import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITblTermine } from 'app/shared/model/tbl-termine.model';

type EntityResponseType = HttpResponse<ITblTermine>;
type EntityArrayResponseType = HttpResponse<ITblTermine[]>;

@Injectable({ providedIn: 'root' })
export class TblTermineService {
    public resourceUrl = SERVER_API_URL + 'api/tbl-termines';

    constructor(private http: HttpClient) {}

    create(tblTermine: ITblTermine): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(tblTermine);
        return this.http
            .post<ITblTermine>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(tblTermine: ITblTermine): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(tblTermine);
        return this.http
            .put<ITblTermine>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITblTermine>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITblTermine[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(tblTermine: ITblTermine): ITblTermine {
        const copy: ITblTermine = Object.assign({}, tblTermine, {
            terIbsDatum:
                tblTermine.terIbsDatum != null && tblTermine.terIbsDatum.isValid() ? tblTermine.terIbsDatum.format(DATE_FORMAT) : null,
            terDatumNeu:
                tblTermine.terDatumNeu != null && tblTermine.terDatumNeu.isValid() ? tblTermine.terDatumNeu.format(DATE_FORMAT) : null,
            terDslFrist:
                tblTermine.terDslFrist != null && tblTermine.terDslFrist.isValid() ? tblTermine.terDslFrist.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.terIbsDatum = res.body.terIbsDatum != null ? moment(res.body.terIbsDatum) : null;
            res.body.terDatumNeu = res.body.terDatumNeu != null ? moment(res.body.terDatumNeu) : null;
            res.body.terDslFrist = res.body.terDslFrist != null ? moment(res.body.terDslFrist) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((tblTermine: ITblTermine) => {
                tblTermine.terIbsDatum = tblTermine.terIbsDatum != null ? moment(tblTermine.terIbsDatum) : null;
                tblTermine.terDatumNeu = tblTermine.terDatumNeu != null ? moment(tblTermine.terDatumNeu) : null;
                tblTermine.terDslFrist = tblTermine.terDslFrist != null ? moment(tblTermine.terDslFrist) : null;
            });
        }
        return res;
    }
}
