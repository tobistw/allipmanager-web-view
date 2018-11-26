import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AllipmanagerwebviewSharedModule } from 'app/shared';
import { AllipmanagerwebviewAdminModule } from 'app/admin/admin.module';
import {
    AbnahmeprotokollComponent,
    AbnahmeprotokollDetailComponent,
    AbnahmeprotokollUpdateComponent,
    AbnahmeprotokollDeletePopupComponent,
    AbnahmeprotokollDeleteDialogComponent,
    abnahmeprotokollRoute,
    abnahmeprotokollPopupRoute
} from './';

const ENTITY_STATES = [...abnahmeprotokollRoute, ...abnahmeprotokollPopupRoute];

@NgModule({
    imports: [AllipmanagerwebviewSharedModule, AllipmanagerwebviewAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AbnahmeprotokollComponent,
        AbnahmeprotokollDetailComponent,
        AbnahmeprotokollUpdateComponent,
        AbnahmeprotokollDeleteDialogComponent,
        AbnahmeprotokollDeletePopupComponent
    ],
    entryComponents: [
        AbnahmeprotokollComponent,
        AbnahmeprotokollUpdateComponent,
        AbnahmeprotokollDeleteDialogComponent,
        AbnahmeprotokollDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AllipmanagerwebviewAbnahmeprotokollModule {}
