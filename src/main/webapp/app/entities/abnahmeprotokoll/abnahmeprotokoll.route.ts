import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Abnahmeprotokoll } from 'app/shared/model/abnahmeprotokoll.model';
import { AbnahmeprotokollService } from './abnahmeprotokoll.service';
import { AbnahmeprotokollComponent } from './abnahmeprotokoll.component';
import { AbnahmeprotokollDetailComponent } from './abnahmeprotokoll-detail.component';
import { AbnahmeprotokollUpdateComponent } from './abnahmeprotokoll-update.component';
import { AbnahmeprotokollDeletePopupComponent } from './abnahmeprotokoll-delete-dialog.component';
import { IAbnahmeprotokoll } from 'app/shared/model/abnahmeprotokoll.model';

@Injectable({ providedIn: 'root' })
export class AbnahmeprotokollResolve implements Resolve<IAbnahmeprotokoll> {
    constructor(private service: AbnahmeprotokollService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Abnahmeprotokoll> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Abnahmeprotokoll>) => response.ok),
                map((abnahmeprotokoll: HttpResponse<Abnahmeprotokoll>) => abnahmeprotokoll.body)
            );
        }
        return of(new Abnahmeprotokoll());
    }
}

export const abnahmeprotokollRoute: Routes = [
    {
        path: 'abnahmeprotokoll',
        component: AbnahmeprotokollComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Abnahmeprotokolls'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'abnahmeprotokoll/:id/view',
        component: AbnahmeprotokollDetailComponent,
        resolve: {
            abnahmeprotokoll: AbnahmeprotokollResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Abnahmeprotokolls'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'abnahmeprotokoll/new',
        component: AbnahmeprotokollUpdateComponent,
        resolve: {
            abnahmeprotokoll: AbnahmeprotokollResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Abnahmeprotokolls'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'abnahmeprotokoll/:id/edit',
        component: AbnahmeprotokollUpdateComponent,
        resolve: {
            abnahmeprotokoll: AbnahmeprotokollResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Abnahmeprotokolls'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const abnahmeprotokollPopupRoute: Routes = [
    {
        path: 'abnahmeprotokoll/:id/delete',
        component: AbnahmeprotokollDeletePopupComponent,
        resolve: {
            abnahmeprotokoll: AbnahmeprotokollResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Abnahmeprotokolls'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
