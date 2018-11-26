import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TblAsp } from 'app/shared/model/tbl-asp.model';
import { TblAspService } from './tbl-asp.service';
import { TblAspComponent } from './tbl-asp.component';
import { TblAspDetailComponent } from './tbl-asp-detail.component';
import { TblAspUpdateComponent } from './tbl-asp-update.component';
import { TblAspDeletePopupComponent } from './tbl-asp-delete-dialog.component';
import { ITblAsp } from 'app/shared/model/tbl-asp.model';

@Injectable({ providedIn: 'root' })
export class TblAspResolve implements Resolve<ITblAsp> {
    constructor(private service: TblAspService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TblAsp> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TblAsp>) => response.ok),
                map((tblAsp: HttpResponse<TblAsp>) => tblAsp.body)
            );
        }
        return of(new TblAsp());
    }
}

export const tblAspRoute: Routes = [
    {
        path: 'tbl-asp',
        component: TblAspComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblAsps'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tbl-asp/:id/view',
        component: TblAspDetailComponent,
        resolve: {
            tblAsp: TblAspResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblAsps'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tbl-asp/new',
        component: TblAspUpdateComponent,
        resolve: {
            tblAsp: TblAspResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblAsps'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tbl-asp/:id/edit',
        component: TblAspUpdateComponent,
        resolve: {
            tblAsp: TblAspResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblAsps'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tblAspPopupRoute: Routes = [
    {
        path: 'tbl-asp/:id/delete',
        component: TblAspDeletePopupComponent,
        resolve: {
            tblAsp: TblAspResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblAsps'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
