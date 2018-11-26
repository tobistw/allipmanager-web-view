/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { TndSiemensDetailComponent } from 'app/entities/tnd-siemens/tnd-siemens-detail.component';
import { TndSiemens } from 'app/shared/model/tnd-siemens.model';

describe('Component Tests', () => {
    describe('TndSiemens Management Detail Component', () => {
        let comp: TndSiemensDetailComponent;
        let fixture: ComponentFixture<TndSiemensDetailComponent>;
        const route = ({ data: of({ tndSiemens: new TndSiemens(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [TndSiemensDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TndSiemensDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TndSiemensDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tndSiemens).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
