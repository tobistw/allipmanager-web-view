import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDashboard } from 'app/shared/model/dashboard.model';

type EntityResponseType = HttpResponse<IDashboard>;
type EntityArrayResponseType = HttpResponse<IDashboard[]>;

@Injectable({ providedIn: 'root' })
export class DashboardService {
    public resourceUrl = SERVER_API_URL + 'api/dashboards';

    constructor(private http: HttpClient) {}

    create(dashboard: IDashboard): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(dashboard);
        return this.http
            .post<IDashboard>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(dashboard: IDashboard): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(dashboard);
        return this.http
            .put<IDashboard>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDashboard>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDashboard[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(dashboard: IDashboard): IDashboard {
        const copy: IDashboard = Object.assign({}, dashboard, {
            zeitstempel: dashboard.zeitstempel != null && dashboard.zeitstempel.isValid() ? dashboard.zeitstempel.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.zeitstempel = res.body.zeitstempel != null ? moment(res.body.zeitstempel) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((dashboard: IDashboard) => {
                dashboard.zeitstempel = dashboard.zeitstempel != null ? moment(dashboard.zeitstempel) : null;
            });
        }
        return res;
    }
}
