import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TblTermine } from 'app/shared/model/tbl-termine.model';
import { TblTermineService } from './tbl-termine.service';
import { TblTermineComponent } from './tbl-termine.component';
import { TblTermineDetailComponent } from './tbl-termine-detail.component';
import { TblTermineUpdateComponent } from './tbl-termine-update.component';
import { TblTermineDeletePopupComponent } from './tbl-termine-delete-dialog.component';
import { ITblTermine } from 'app/shared/model/tbl-termine.model';

@Injectable({ providedIn: 'root' })
export class TblTermineResolve implements Resolve<ITblTermine> {
    constructor(private service: TblTermineService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TblTermine> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TblTermine>) => response.ok),
                map((tblTermine: HttpResponse<TblTermine>) => tblTermine.body)
            );
        }
        return of(new TblTermine());
    }
}

export const tblTermineRoute: Routes = [
    {
        path: 'tbl-termine',
        component: TblTermineComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblTermines'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tbl-termine/:id/view',
        component: TblTermineDetailComponent,
        resolve: {
            tblTermine: TblTermineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblTermines'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tbl-termine/new',
        component: TblTermineUpdateComponent,
        resolve: {
            tblTermine: TblTermineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblTermines'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tbl-termine/:id/edit',
        component: TblTermineUpdateComponent,
        resolve: {
            tblTermine: TblTermineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblTermines'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tblTerminePopupRoute: Routes = [
    {
        path: 'tbl-termine/:id/delete',
        component: TblTermineDeletePopupComponent,
        resolve: {
            tblTermine: TblTermineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblTermines'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
