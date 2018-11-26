/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { TblFunkmessungUpdateComponent } from 'app/entities/tbl-funkmessung/tbl-funkmessung-update.component';
import { TblFunkmessungService } from 'app/entities/tbl-funkmessung/tbl-funkmessung.service';
import { TblFunkmessung } from 'app/shared/model/tbl-funkmessung.model';

describe('Component Tests', () => {
    describe('TblFunkmessung Management Update Component', () => {
        let comp: TblFunkmessungUpdateComponent;
        let fixture: ComponentFixture<TblFunkmessungUpdateComponent>;
        let service: TblFunkmessungService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [TblFunkmessungUpdateComponent]
            })
                .overrideTemplate(TblFunkmessungUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TblFunkmessungUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TblFunkmessungService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TblFunkmessung(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tblFunkmessung = entity;
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
                    const entity = new TblFunkmessung();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tblFunkmessung = entity;
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
