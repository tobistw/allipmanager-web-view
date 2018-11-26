import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Arbeitsbeleg } from 'app/shared/model/arbeitsbeleg.model';
import { ArbeitsbelegService } from './arbeitsbeleg.service';
import { ArbeitsbelegComponent } from './arbeitsbeleg.component';
import { ArbeitsbelegDetailComponent } from './arbeitsbeleg-detail.component';
import { ArbeitsbelegUpdateComponent } from './arbeitsbeleg-update.component';
import { ArbeitsbelegDeletePopupComponent } from './arbeitsbeleg-delete-dialog.component';
import { IArbeitsbeleg } from 'app/shared/model/arbeitsbeleg.model';

@Injectable({ providedIn: 'root' })
export class ArbeitsbelegResolve implements Resolve<IArbeitsbeleg> {
    constructor(private service: ArbeitsbelegService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Arbeitsbeleg> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Arbeitsbeleg>) => response.ok),
                map((arbeitsbeleg: HttpResponse<Arbeitsbeleg>) => arbeitsbeleg.body)
            );
        }
        return of(new Arbeitsbeleg());
    }
}

export const arbeitsbelegRoute: Routes = [
    {
        path: 'arbeitsbeleg',
        component: ArbeitsbelegComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Arbeitsbelegs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'arbeitsbeleg/:id/view',
        component: ArbeitsbelegDetailComponent,
        resolve: {
            arbeitsbeleg: ArbeitsbelegResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Arbeitsbelegs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'arbeitsbeleg/new',
        component: ArbeitsbelegUpdateComponent,
        resolve: {
            arbeitsbeleg: ArbeitsbelegResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Arbeitsbelegs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'arbeitsbeleg/:id/edit',
        component: ArbeitsbelegUpdateComponent,
        resolve: {
            arbeitsbeleg: ArbeitsbelegResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Arbeitsbelegs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const arbeitsbelegPopupRoute: Routes = [
    {
        path: 'arbeitsbeleg/:id/delete',
        component: ArbeitsbelegDeletePopupComponent,
        resolve: {
            arbeitsbeleg: ArbeitsbelegResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Arbeitsbelegs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
