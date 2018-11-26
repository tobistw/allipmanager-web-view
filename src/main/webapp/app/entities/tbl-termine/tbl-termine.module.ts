import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AllipmanagerwebviewSharedModule } from 'app/shared';
import { AllipmanagerwebviewAdminModule } from 'app/admin/admin.module';
import {
    TblTermineComponent,
    TblTermineDetailComponent,
    TblTermineUpdateComponent,
    TblTermineDeletePopupComponent,
    TblTermineDeleteDialogComponent,
    tblTermineRoute,
    tblTerminePopupRoute
} from './';

const ENTITY_STATES = [...tblTermineRoute, ...tblTerminePopupRoute];

@NgModule({
    imports: [AllipmanagerwebviewSharedModule, AllipmanagerwebviewAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TblTermineComponent,
        TblTermineDetailComponent,
        TblTermineUpdateComponent,
        TblTermineDeleteDialogComponent,
        TblTermineDeletePopupComponent
    ],
    entryComponents: [TblTermineComponent, TblTermineUpdateComponent, TblTermineDeleteDialogComponent, TblTermineDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AllipmanagerwebviewTblTermineModule {}
