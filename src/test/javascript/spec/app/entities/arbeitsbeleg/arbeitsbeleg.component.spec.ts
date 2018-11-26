/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { ArbeitsbelegComponent } from 'app/entities/arbeitsbeleg/arbeitsbeleg.component';
import { ArbeitsbelegService } from 'app/entities/arbeitsbeleg/arbeitsbeleg.service';
import { Arbeitsbeleg } from 'app/shared/model/arbeitsbeleg.model';

describe('Component Tests', () => {
    describe('Arbeitsbeleg Management Component', () => {
        let comp: ArbeitsbelegComponent;
        let fixture: ComponentFixture<ArbeitsbelegComponent>;
        let service: ArbeitsbelegService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [ArbeitsbelegComponent],
                providers: []
            })
                .overrideTemplate(ArbeitsbelegComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ArbeitsbelegComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ArbeitsbelegService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Arbeitsbeleg(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.arbeitsbelegs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
