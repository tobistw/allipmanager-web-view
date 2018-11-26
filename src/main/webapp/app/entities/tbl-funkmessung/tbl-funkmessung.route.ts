import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TblFunkmessung } from 'app/shared/model/tbl-funkmessung.model';
import { TblFunkmessungService } from './tbl-funkmessung.service';
import { TblFunkmessungComponent } from './tbl-funkmessung.component';
import { TblFunkmessungDetailComponent } from './tbl-funkmessung-detail.component';
import { TblFunkmessungUpdateComponent } from './tbl-funkmessung-update.component';
import { TblFunkmessungDeletePopupComponent } from './tbl-funkmessung-delete-dialog.component';
import { ITblFunkmessung } from 'app/shared/model/tbl-funkmessung.model';

@Injectable({ providedIn: 'root' })
export class TblFunkmessungResolve implements Resolve<ITblFunkmessung> {
    constructor(private service: TblFunkmessungService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TblFunkmessung> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TblFunkmessung>) => response.ok),
                map((tblFunkmessung: HttpResponse<TblFunkmessung>) => tblFunkmessung.body)
            );
        }
        return of(new TblFunkmessung());
    }
}

export const tblFunkmessungRoute: Routes = [
    {
        path: 'tbl-funkmessung',
        component: TblFunkmessungComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblFunkmessungs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tbl-funkmessung/:id/view',
        component: TblFunkmessungDetailComponent,
        resolve: {
            tblFunkmessung: TblFunkmessungResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblFunkmessungs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tbl-funkmessung/new',
        component: TblFunkmessungUpdateComponent,
        resolve: {
            tblFunkmessung: TblFunkmessungResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblFunkmessungs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tbl-funkmessung/:id/edit',
        component: TblFunkmessungUpdateComponent,
        resolve: {
            tblFunkmessung: TblFunkmessungResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblFunkmessungs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tblFunkmessungPopupRoute: Routes = [
    {
        path: 'tbl-funkmessung/:id/delete',
        component: TblFunkmessungDeletePopupComponent,
        resolve: {
            tblFunkmessung: TblFunkmessungResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblFunkmessungs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
