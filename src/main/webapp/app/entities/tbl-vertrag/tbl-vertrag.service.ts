import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITblVertrag } from 'app/shared/model/tbl-vertrag.model';

type EntityResponseType = HttpResponse<ITblVertrag>;
type EntityArrayResponseType = HttpResponse<ITblVertrag[]>;

@Injectable({ providedIn: 'root' })
export class TblVertragService {
    public resourceUrl = SERVER_API_URL + 'api/tbl-vertrags';

    constructor(private http: HttpClient) {}

    create(tblVertrag: ITblVertrag): Observable<EntityResponseType> {
        return this.http.post<ITblVertrag>(this.resourceUrl, tblVertrag, { observe: 'response' });
    }

    update(tblVertrag: ITblVertrag): Observable<EntityResponseType> {
        return this.http.put<ITblVertrag>(this.resourceUrl, tblVertrag, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITblVertrag>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITblVertrag[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
