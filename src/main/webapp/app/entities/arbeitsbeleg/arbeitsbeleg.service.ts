import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IArbeitsbeleg } from 'app/shared/model/arbeitsbeleg.model';

type EntityResponseType = HttpResponse<IArbeitsbeleg>;
type EntityArrayResponseType = HttpResponse<IArbeitsbeleg[]>;

@Injectable({ providedIn: 'root' })
export class ArbeitsbelegService {
    public resourceUrl = SERVER_API_URL + 'api/arbeitsbelegs';

    constructor(private http: HttpClient) {}

    create(arbeitsbeleg: IArbeitsbeleg): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(arbeitsbeleg);
        return this.http
            .post<IArbeitsbeleg>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(arbeitsbeleg: IArbeitsbeleg): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(arbeitsbeleg);
        return this.http
            .put<IArbeitsbeleg>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IArbeitsbeleg>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IArbeitsbeleg[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(arbeitsbeleg: IArbeitsbeleg): IArbeitsbeleg {
        const copy: IArbeitsbeleg = Object.assign({}, arbeitsbeleg, {
            datumLeistung:
                arbeitsbeleg.datumLeistung != null && arbeitsbeleg.datumLeistung.isValid()
                    ? arbeitsbeleg.datumLeistung.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.datumLeistung = res.body.datumLeistung != null ? moment(res.body.datumLeistung) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((arbeitsbeleg: IArbeitsbeleg) => {
                arbeitsbeleg.datumLeistung = arbeitsbeleg.datumLeistung != null ? moment(arbeitsbeleg.datumLeistung) : null;
            });
        }
        return res;
    }
}
