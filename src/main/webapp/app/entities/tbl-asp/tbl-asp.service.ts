import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITblAsp } from 'app/shared/model/tbl-asp.model';

type EntityResponseType = HttpResponse<ITblAsp>;
type EntityArrayResponseType = HttpResponse<ITblAsp[]>;

@Injectable({ providedIn: 'root' })
export class TblAspService {
    public resourceUrl = SERVER_API_URL + 'api/tbl-asps';

    constructor(private http: HttpClient) {}

    create(tblAsp: ITblAsp): Observable<EntityResponseType> {
        return this.http.post<ITblAsp>(this.resourceUrl, tblAsp, { observe: 'response' });
    }

    update(tblAsp: ITblAsp): Observable<EntityResponseType> {
        return this.http.put<ITblAsp>(this.resourceUrl, tblAsp, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITblAsp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITblAsp[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
