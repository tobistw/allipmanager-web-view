/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { ArbeitsbelegDetailComponent } from 'app/entities/arbeitsbeleg/arbeitsbeleg-detail.component';
import { Arbeitsbeleg } from 'app/shared/model/arbeitsbeleg.model';

describe('Component Tests', () => {
    describe('Arbeitsbeleg Management Detail Component', () => {
        let comp: ArbeitsbelegDetailComponent;
        let fixture: ComponentFixture<ArbeitsbelegDetailComponent>;
        const route = ({ data: of({ arbeitsbeleg: new Arbeitsbeleg(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [ArbeitsbelegDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ArbeitsbelegDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ArbeitsbelegDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.arbeitsbeleg).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
