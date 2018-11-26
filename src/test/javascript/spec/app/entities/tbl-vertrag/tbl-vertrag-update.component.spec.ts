/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { TblVertragUpdateComponent } from 'app/entities/tbl-vertrag/tbl-vertrag-update.component';
import { TblVertragService } from 'app/entities/tbl-vertrag/tbl-vertrag.service';
import { TblVertrag } from 'app/shared/model/tbl-vertrag.model';

describe('Component Tests', () => {
    describe('TblVertrag Management Update Component', () => {
        let comp: TblVertragUpdateComponent;
        let fixture: ComponentFixture<TblVertragUpdateComponent>;
        let service: TblVertragService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [TblVertragUpdateComponent]
            })
                .overrideTemplate(TblVertragUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TblVertragUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TblVertragService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TblVertrag(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tblVertrag = entity;
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
                    const entity = new TblVertrag();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tblVertrag = entity;
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
