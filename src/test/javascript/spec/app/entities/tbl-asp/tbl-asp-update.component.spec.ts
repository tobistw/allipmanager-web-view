/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { TblAspUpdateComponent } from 'app/entities/tbl-asp/tbl-asp-update.component';
import { TblAspService } from 'app/entities/tbl-asp/tbl-asp.service';
import { TblAsp } from 'app/shared/model/tbl-asp.model';

describe('Component Tests', () => {
    describe('TblAsp Management Update Component', () => {
        let comp: TblAspUpdateComponent;
        let fixture: ComponentFixture<TblAspUpdateComponent>;
        let service: TblAspService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [TblAspUpdateComponent]
            })
                .overrideTemplate(TblAspUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TblAspUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TblAspService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TblAsp(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tblAsp = entity;
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
                    const entity = new TblAsp();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tblAsp = entity;
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
