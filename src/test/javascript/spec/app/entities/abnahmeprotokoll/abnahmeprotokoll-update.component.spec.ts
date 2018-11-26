/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { AbnahmeprotokollUpdateComponent } from 'app/entities/abnahmeprotokoll/abnahmeprotokoll-update.component';
import { AbnahmeprotokollService } from 'app/entities/abnahmeprotokoll/abnahmeprotokoll.service';
import { Abnahmeprotokoll } from 'app/shared/model/abnahmeprotokoll.model';

describe('Component Tests', () => {
    describe('Abnahmeprotokoll Management Update Component', () => {
        let comp: AbnahmeprotokollUpdateComponent;
        let fixture: ComponentFixture<AbnahmeprotokollUpdateComponent>;
        let service: AbnahmeprotokollService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [AbnahmeprotokollUpdateComponent]
            })
                .overrideTemplate(AbnahmeprotokollUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AbnahmeprotokollUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AbnahmeprotokollService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Abnahmeprotokoll(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.abnahmeprotokoll = entity;
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
                    const entity = new Abnahmeprotokoll();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.abnahmeprotokoll = entity;
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
