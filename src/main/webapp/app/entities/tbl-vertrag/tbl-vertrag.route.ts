import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TblVertrag } from 'app/shared/model/tbl-vertrag.model';
import { TblVertragService } from './tbl-vertrag.service';
import { TblVertragComponent } from './tbl-vertrag.component';
import { TblVertragDetailComponent } from './tbl-vertrag-detail.component';
import { TblVertragUpdateComponent } from './tbl-vertrag-update.component';
import { TblVertragDeletePopupComponent } from './tbl-vertrag-delete-dialog.component';
import { ITblVertrag } from 'app/shared/model/tbl-vertrag.model';

@Injectable({ providedIn: 'root' })
export class TblVertragResolve implements Resolve<ITblVertrag> {
    constructor(private service: TblVertragService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TblVertrag> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TblVertrag>) => response.ok),
                map((tblVertrag: HttpResponse<TblVertrag>) => tblVertrag.body)
            );
        }
        return of(new TblVertrag());
    }
}

export const tblVertragRoute: Routes = [
    {
        path: 'tbl-vertrag',
        component: TblVertragComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblVertrags'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tbl-vertrag/:id/view',
        component: TblVertragDetailComponent,
        resolve: {
            tblVertrag: TblVertragResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblVertrags'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tbl-vertrag/new',
        component: TblVertragUpdateComponent,
        resolve: {
            tblVertrag: TblVertragResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblVertrags'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tbl-vertrag/:id/edit',
        component: TblVertragUpdateComponent,
        resolve: {
            tblVertrag: TblVertragResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblVertrags'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tblVertragPopupRoute: Routes = [
    {
        path: 'tbl-vertrag/:id/delete',
        component: TblVertragDeletePopupComponent,
        resolve: {
            tblVertrag: TblVertragResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TblVertrags'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
