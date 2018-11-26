/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { TblTermineUpdateComponent } from 'app/entities/tbl-termine/tbl-termine-update.component';
import { TblTermineService } from 'app/entities/tbl-termine/tbl-termine.service';
import { TblTermine } from 'app/shared/model/tbl-termine.model';

describe('Component Tests', () => {
    describe('TblTermine Management Update Component', () => {
        let comp: TblTermineUpdateComponent;
        let fixture: ComponentFixture<TblTermineUpdateComponent>;
        let service: TblTermineService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [TblTermineUpdateComponent]
            })
                .overrideTemplate(TblTermineUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TblTermineUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TblTermineService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TblTermine(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tblTermine = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TblTermine();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tblTermine = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
