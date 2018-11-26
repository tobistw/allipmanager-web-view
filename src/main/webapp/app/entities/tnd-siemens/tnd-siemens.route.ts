import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TndSiemens } from 'app/shared/model/tnd-siemens.model';
import { TndSiemensService } from './tnd-siemens.service';
import { TndSiemensComponent } from './tnd-siemens.component';
import { TndSiemensDetailComponent } from './tnd-siemens-detail.component';
import { TndSiemensUpdateComponent } from './tnd-siemens-update.component';
import { TndSiemensDeletePopupComponent } from './tnd-siemens-delete-dialog.component';
import { ITndSiemens } from 'app/shared/model/tnd-siemens.model';

@Injectable({ providedIn: 'root' })
export class TndSiemensResolve implements Resolve<ITndSiemens> {
    constructor(private service: TndSiemensService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TndSiemens> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TndSiemens>) => response.ok),
                map((tndSiemens: HttpResponse<TndSiemens>) => tndSiemens.body)
            );
        }
        return of(new TndSiemens());
    }
}

export const tndSiemensRoute: Routes = [
    {
        path: 'tnd-siemens',
        component: TndSiemensComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TndSiemens'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tnd-siemens/:id/view',
        component: TndSiemensDetailComponent,
        resolve: {
            tndSiemens: TndSiemensResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TndSiemens'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tnd-siemens/new',
        component: TndSiemensUpdateComponent,
        resolve: {
            tndSiemens: TndSiemensResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TndSiemens'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tnd-siemens/:id/edit',
        component: TndSiemensUpdateComponent,
        resolve: {
            tndSiemens: TndSiemensResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TndSiemens'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tndSiemensPopupRoute: Routes = [
    {
        path: 'tnd-siemens/:id/delete',
        component: TndSiemensDeletePopupComponent,
        resolve: {
            tndSiemens: TndSiemensResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TndSiemens'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
