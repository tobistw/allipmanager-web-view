/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { TndSiemensUpdateComponent } from 'app/entities/tnd-siemens/tnd-siemens-update.component';
import { TndSiemensService } from 'app/entities/tnd-siemens/tnd-siemens.service';
import { TndSiemens } from 'app/shared/model/tnd-siemens.model';

describe('Component Tests', () => {
    describe('TndSiemens Management Update Component', () => {
        let comp: TndSiemensUpdateComponent;
        let fixture: ComponentFixture<TndSiemensUpdateComponent>;
        let service: TndSiemensService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [TndSiemensUpdateComponent]
            })
                .overrideTemplate(TndSiemensUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TndSiemensUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TndSiemensService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TndSiemens(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tndSiemens = entity;
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
                    const entity = new TndSiemens();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tndSiemens = entity;
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
