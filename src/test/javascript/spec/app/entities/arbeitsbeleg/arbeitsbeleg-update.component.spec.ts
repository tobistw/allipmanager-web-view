/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { ArbeitsbelegUpdateComponent } from 'app/entities/arbeitsbeleg/arbeitsbeleg-update.component';
import { ArbeitsbelegService } from 'app/entities/arbeitsbeleg/arbeitsbeleg.service';
import { Arbeitsbeleg } from 'app/shared/model/arbeitsbeleg.model';

describe('Component Tests', () => {
    describe('Arbeitsbeleg Management Update Component', () => {
        let comp: ArbeitsbelegUpdateComponent;
        let fixture: ComponentFixture<ArbeitsbelegUpdateComponent>;
        let service: ArbeitsbelegService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [ArbeitsbelegUpdateComponent]
            })
                .overrideTemplate(ArbeitsbelegUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ArbeitsbelegUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ArbeitsbelegService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Arbeitsbeleg(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.arbeitsbeleg = entity;
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
                    const entity = new Arbeitsbeleg();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.arbeitsbeleg = entity;
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
