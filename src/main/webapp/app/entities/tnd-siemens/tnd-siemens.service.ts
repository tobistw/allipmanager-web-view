import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITndSiemens } from 'app/shared/model/tnd-siemens.model';

type EntityResponseType = HttpResponse<ITndSiemens>;
type EntityArrayResponseType = HttpResponse<ITndSiemens[]>;

@Injectable({ providedIn: 'root' })
export class TndSiemensService {
    public resourceUrl = SERVER_API_URL + 'api/tnd-siemens';

    constructor(private http: HttpClient) {}

    create(tndSiemens: ITndSiemens): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(tndSiemens);
        return this.http
            .post<ITndSiemens>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(tndSiemens: ITndSiemens): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(tndSiemens);
        return this.http
            .put<ITndSiemens>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITndSiemens>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITndSiemens[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(tndSiemens: ITndSiemens): ITndSiemens {
        const copy: ITndSiemens = Object.assign({}, tndSiemens, {
            tndWarten: tndSiemens.tndWarten != null && tndSiemens.tndWarten.isValid() ? tndSiemens.tndWarten.format(DATE_FORMAT) : null,
            tndDslFrist:
                tndSiemens.tndDslFrist != null && tndSiemens.tndDslFrist.isValid() ? tndSiemens.tndDslFrist.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.tndWarten = res.body.tndWarten != null ? moment(res.body.tndWarten) : null;
            res.body.tndDslFrist = res.body.tndDslFrist != null ? moment(res.body.tndDslFrist) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((tndSiemens: ITndSiemens) => {
                tndSiemens.tndWarten = tndSiemens.tndWarten != null ? moment(tndSiemens.tndWarten) : null;
                tndSiemens.tndDslFrist = tndSiemens.tndDslFrist != null ? moment(tndSiemens.tndDslFrist) : null;
            });
        }
        return res;
    }
}
